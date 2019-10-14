package com.clouderwork.interceptor;

import com.clouderwork.common.MD5;
import com.clouderwork.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * @Author : xuqiang
 * @Description : 登录拦截
 * @Date: 2017/8/4
 */
public class LoginInterceptor implements HandlerInterceptor{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Autowired
	private RedisService redis;

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//		if(!checkSign(request)){
//			returnJson(response, JSON.toJSONString(new CommResult().error(ResultCodeEnum.UNAUTHORIZED.getValue(),
//					"请求不合法",null)));
//			return false;
//		}
//
//		Long minusTime = System.currentTimeMillis()-Long.valueOf(request.getHeader("timestamp"));
//		if(minusTime>60000){//超过60s请求失效
//			returnJson(response, JSON.toJSONString(new CommResult().error(ResultCodeEnum.UNAUTHORIZED.getValue(),
//					"请求超时",null)));
//			return false;
//		}
//
//		String token = request.getHeader("token");
//		if(StringUtils.isEmpty(token)){
//			returnJson(response, JSON.toJSONString(new CommResult().error(ResultCodeEnum.UNAUTHORIZED.getValue(),
//                    ResultCodeEnum.UNAUTHORIZED.getText(),null)));
//			return false;
//		}
//		LoginUser user = (LoginUser)redis.getObject(token);
//		if(user != null){
//			return true;
//		} else {
//			returnJson(response, JSON.toJSONString(
//					new CommResult().error(ResultCodeEnum.UNAUTHORIZED.getValue(),
//                            ResultCodeEnum.UNAUTHORIZED.getText(),null)));
//			return false;
//		}
		return true;
	}


	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	private void returnJson(HttpServletResponse response, String json) throws Exception{
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			writer = response.getWriter();
			writer.print(json);
		} catch (Exception e) {
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	private Boolean checkSign(HttpServletRequest request){
		//先判断时间戳和MD5
		String timestamp = request.getHeader("timestamp");
		String sign = request.getHeader("sign");
		String token = request.getHeader("token");
		if(StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(sign) || !isNumeric(timestamp) || StringUtils.isEmpty(token)){
			return false;
		}
		String serverSign = new MD5().getMD5ofStr(token+"api_1.0.0"+timestamp);
		if(!sign.equalsIgnoreCase(serverSign)){
			return false;
		}
		return true;
	}

	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
