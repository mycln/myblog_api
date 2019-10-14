package com.clouderwork.interceptor;

import com.clouderwork.annotation.DuplicateSubmitToken;
import com.clouderwork.service.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.InterruptedIOException;

/**
 * @description 防止表单重复提交拦截器
 */
@Aspect
@Component
public class DuplicateSubmitAspect {
	public static final String  DUPLICATE_TOKEN_KEY="duplicate_token_key";

    @Autowired
    private RedisService redisService;

	@Pointcut("execution(public * com.clouderwork.api..*(..))")
	public void webLog() {
	}

	@Before("webLog() && @annotation(token)")
	public void before(final JoinPoint joinPoint, DuplicateSubmitToken token) throws Exception{
		if (token!=null){
			boolean isSaveSession=token.save();
			if (isSaveSession){
				String key = getDuplicateTokenKey(joinPoint);
				if (StringUtils.isEmpty(redisService.getStr(key))){
                    redisService.setStr(key,key);
                    redisService.expire(key,10);
				}else {
					throw new InterruptedIOException("请稍后......");
				}
			}
		}
	}

	/**
	 * 获取重复提交key
	 * @param joinPoint
	 * @return
	 */
	public String getDuplicateTokenKey(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
		StringBuilder key=new StringBuilder(DUPLICATE_TOKEN_KEY);
		key.append(",").append(methodName).append(request.getSession().getId());
		return key.toString();
	}

	@AfterReturning("webLog() && @annotation(token)")
	public void doAfterReturning(JoinPoint joinPoint,DuplicateSubmitToken token) {
		// 处理完请求，返回内容
		if (token!=null){
			boolean isSaveSession=token.save();
			if (isSaveSession){
				String key = getDuplicateTokenKey(joinPoint);
				if (!StringUtils.isEmpty(redisService.getStr(key)) && token.type()==DuplicateSubmitToken.REQUEST){
                    redisService.delete(key);
				}
			}
		}
	}

	/**
	 * 异常
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "webLog()&& @annotation(token)", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e, DuplicateSubmitToken token) {
		if (null!=token && e instanceof IllegalArgumentException == true){
			//处理处理重复提交本身之外的异常
			boolean isSaveSession=token.save();
			//获得方法名称
			if (isSaveSession){
				String key=getDuplicateTokenKey(joinPoint);
				if (!StringUtils.isEmpty(redisService.getStr(key))){
					redisService.delete(key);
				}
			}
		}
	}
}
