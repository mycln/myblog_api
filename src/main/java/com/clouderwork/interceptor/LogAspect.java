package com.clouderwork.interceptor;
import com.alibaba.fastjson.JSON;
import com.clouderwork.annotation.ControllerLogs;
import com.clouderwork.annotation.ServiceLogs;
import com.clouderwork.common.IpUtils;
import com.clouderwork.common.StringUtils;
import com.clouderwork.common.UserAgentUtils;
import com.clouderwork.enums.IsException;
import com.clouderwork.enums.IsLogin;
import com.clouderwork.pojo.vo.LoginUser;
import com.clouderwork.pojo.vo.SysLogsVo;
import com.clouderwork.service.CommonService;
import com.clouderwork.service.SysLogsService;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @version: V1.0
 * @author: heyanfeng
 * @className: LogAspect
 * @packageName: com.xxxx.logs.aop
 * @description: 日志切点
 * @data: 2018-11-20 10:15
 **/
@Aspect
@Configuration
public class LogAspect {
    @Value("${save.log}")
    private boolean saveLog;

    @Value("${print.log}")
    private boolean printLog;

    @Autowired
    private SysLogsVo sysLogsVo;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysLogsService sysLogsService;
    /**
     * 本地异常日志记录对象
     */
    private  final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.clouderwork.annotation.ControllerLogs)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        if(!saveLog && !printLog){
            return;
        }
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            //类名
            String className = joinPoint.getTarget().getClass().getName();
            //请求方法
            String method =  joinPoint.getSignature().getName() + "()";
            //方法参数
            String methodParam = JSON.toJSONString(joinPoint.getArgs());
            Map<String, String[]> params = request.getParameterMap();
            String decode = "";
            //针对get请求
            if(request.getQueryString()!=null){
                try {
                    decode = URLDecoder.decode(request.getQueryString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                //针对post请求
                for (String key : params.keySet()) {
                    String[] values = params.get(key);
                    for (int i = 0; i < values.length; i++) {
                        String value = values[i];
                        decode += key + "=" + value + "&";
                    }
                }
            }
            //将String根据&转成Map
            Map<String, Object> methodParamMap = transStringToMap(decode, "&", "=");
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //方法描述
            String methodDescription = getControllerMethodDescription(joinPoint);
            StringBuilder sb = new StringBuilder(1000);
            setSysLogs(request, className, method, methodParam, decode, methodParamMap, methodDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) throws Throwable {
        if(!saveLog && !printLog){
            return;
        }
        if(sysLogsVo.getIsException()==IsException.NOEXCEPTION.getValue()){
            if(saveLog){
                sysLogsService.add(sysLogsVo);
            }
            if(printLog){
                logger.info("日志没有发生异常{}",sysLogsVo);
            }
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Exception {
        if(!saveLog && !printLog){
            return;
        }
        setSysLogThrow(ex);

        if(saveLog){
            sysLogsService.add(sysLogsVo);
        }
        if(printLog){
            logger.info("日志发生异常{}",sysLogsVo);
        }
    }

    /**
     *  异常之后set log 属性
     * @param ex
     */
    public void setSysLogThrow(Throwable ex) {
        sysLogsVo.setIsException(IsException.EXCEPTION.getValue());
        sysLogsVo.setExceptionName(ex.getClass().getName());
        sysLogsVo.setExceptionMsg(ex.getMessage());
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceLogs.class).description();
                    break;
                }
            }
        }
        return description;
    }
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControllerLogs.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * String 转Map
     * @param mapString 待转的String
     * @param separator 分割符
     * @param pairSeparator 分离器
     * @return
     */
    public static Map<String, Object> transStringToMap(String mapString, String separator, String pairSeparator) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] fSplit = mapString.split(separator);
        for (int i = 0; i < fSplit.length; i++) {
            if (fSplit[i]==null||fSplit[i].length()==0) {
                continue;
            }
            String[] sSplit = fSplit[i].split(pairSeparator);
            String value = fSplit[i].substring(fSplit[i].indexOf('=') + 1, fSplit[i].length());
            map.put(sSplit[0], value);
        }
        return map;
    }

    public SysLogsVo getSysLogsVo() {
        return sysLogsVo;
    }

    public void setSysLogsVo(SysLogsVo sysLogsVo) {
        this.sysLogsVo = sysLogsVo;
    }

    /**
     *  set sysLogs属性
     * @param request
     * @param className
     * @param method
     * @param methodParam
     * @param decode
     * @param methodParamMap
     * @param methodDescription
     */
    public void setSysLogs(HttpServletRequest request, String className, String method, String methodParam, String decode, Map<String, Object> methodParamMap, String methodDescription) {
        sysLogsVo.setAddTime(new Date());
        sysLogsVo.setClassName(className);
        sysLogsVo.setMethod(method);
        sysLogsVo.setContentType(("".equals(request.getContentType()) || request.getContentType() == null)?"FROM":request.getContentType());
        sysLogsVo.setRequestParams(("".equals(decode) || decode == null)?methodParam:methodParamMap.toString());
        sysLogsVo.setRequestType(request.getMethod());
        sysLogsVo.setDescription(methodDescription);
        sysLogsVo.setServerAddr(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
        sysLogsVo.setRemoteAddr(IpUtils.getRemoteAddr(request));
        UserAgent userAgent = UserAgentUtils.getUserAgent(request);
        sysLogsVo.setDeviceName(userAgent.getOperatingSystem().getName());
        sysLogsVo.setBrowserName(userAgent.getBrowser().getName());
        sysLogsVo.setUserAgent(request.getHeader("User-Agent"));
        sysLogsVo.setRequestUri(StringUtils.abbr(request.getRequestURI(), 255));
        LoginUser curreUser = commonService.getCurreUser();
        if(curreUser==null){
            sysLogsVo.setIsLogin(IsLogin.NOLOGIN.getValue());
        }else{
            sysLogsVo.setIsLogin(IsLogin.LOGIN.getValue());
            sysLogsVo.setUserId(curreUser.getId());
        }
        sysLogsVo.setIsException(IsException.NOEXCEPTION.getValue());
    }
}

