package com.clouderwork.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysLogs implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 请求controller的名称
     */
    private String className;

    /**
     * 请求controller的方法
     */
    private String method;

    /**
     * http请求content-type
     */
    private String contentType;

    /**
     * http请求类型
     */
    private String requestType;

    /**
     * 请求方法的描述
     */
    private String description;

    /**
     * 请求接口地址
     */
    private String serverAddr;

    /**
     * 发出请求的主机的 IP 地址
     */
    private String remoteAddr;

    /**
     * 请求来源使用的设备
     */
    private String deviceName;

    /**
     * 请求来源使用的浏览器
     */
    private String browserName;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求方法的路径
     */
    private String requestUri;

    /**
     * 用户是否登录
     */
    private Integer isLogin;

    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 日志添加时间
     */
    private Date addTime;

    /**
     * 是否异常 0:无异常；1发生异常
     */
    private Integer isException;

    /**
     * 异常发生的类的名称
     */
    private String exceptionName;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    /**
     * sys_logs
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 请求controller的名称
     * @return class_name 请求controller的名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 请求controller的名称
     * @param className 请求controller的名称
     */
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    /**
     * 请求controller的方法
     * @return method 请求controller的方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 请求controller的方法
     * @param method 请求controller的方法
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * http请求content-type
     * @return content_type http请求content-type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * http请求content-type
     * @param contentType http请求content-type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    /**
     * http请求类型
     * @return request_type http请求类型
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * http请求类型
     * @param requestType http请求类型
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    /**
     * 请求方法的描述
     * @return description 请求方法的描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 请求方法的描述
     * @param description 请求方法的描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 请求接口地址
     * @return server_addr 请求接口地址
     */
    public String getServerAddr() {
        return serverAddr;
    }

    /**
     * 请求接口地址
     * @param serverAddr 请求接口地址
     */
    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr == null ? null : serverAddr.trim();
    }

    /**
     * 发出请求的主机的 IP 地址
     * @return remote_addr 发出请求的主机的 IP 地址
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * 发出请求的主机的 IP 地址
     * @param remoteAddr 发出请求的主机的 IP 地址
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    /**
     * 请求来源使用的设备
     * @return device_name 请求来源使用的设备
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 请求来源使用的设备
     * @param deviceName 请求来源使用的设备
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    /**
     * 请求来源使用的浏览器
     * @return browser_name 请求来源使用的浏览器
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * 请求来源使用的浏览器
     * @param browserName 请求来源使用的浏览器
     */
    public void setBrowserName(String browserName) {
        this.browserName = browserName == null ? null : browserName.trim();
    }

    /**
     * 用户代理
     * @return user_agent 用户代理
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 用户代理
     * @param userAgent 用户代理
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * 请求方法的路径
     * @return request_uri 请求方法的路径
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * 请求方法的路径
     * @param requestUri 请求方法的路径
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * 用户是否登录
     * @return is_login 用户是否登录
     */
    public Integer getIsLogin() {
        return isLogin;
    }

    /**
     * 用户是否登录
     * @param isLogin 用户是否登录
     */
    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    /**
     * 用户的id
     * @return user_id 用户的id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户的id
     * @param userId 用户的id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 日志添加时间
     * @return add_time 日志添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 日志添加时间
     * @param addTime 日志添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 是否异常 0:无异常；1发生异常
     * @return is_exception 是否异常 0:无异常；1发生异常
     */
    public Integer getIsException() {
        return isException;
    }

    /**
     * 是否异常 0:无异常；1发生异常
     * @param isException 是否异常 0:无异常；1发生异常
     */
    public void setIsException(Integer isException) {
        this.isException = isException;
    }

    /**
     * 异常发生的类的名称
     * @return exception_name 异常发生的类的名称
     */
    public String getExceptionName() {
        return exceptionName;
    }

    /**
     * 异常发生的类的名称
     * @param exceptionName 异常发生的类的名称
     */
    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName == null ? null : exceptionName.trim();
    }

    /**
     * 请求参数
     * @return request_params 请求参数
     */
    public String getRequestParams() {
        return requestParams;
    }

    /**
     * 请求参数
     * @param requestParams 请求参数
     */
    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams == null ? null : requestParams.trim();
    }

    /**
     * 异常信息
     * @return exception_msg 异常信息
     */
    public String getExceptionMsg() {
        return exceptionMsg;
    }

    /**
     * 异常信息
     * @param exceptionMsg 异常信息
     */
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg == null ? null : exceptionMsg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", className=").append(className);
        sb.append(", method=").append(method);
        sb.append(", contentType=").append(contentType);
        sb.append(", requestType=").append(requestType);
        sb.append(", description=").append(description);
        sb.append(", serverAddr=").append(serverAddr);
        sb.append(", remoteAddr=").append(remoteAddr);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", browserName=").append(browserName);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", requestUri=").append(requestUri);
        sb.append(", isLogin=").append(isLogin);
        sb.append(", userId=").append(userId);
        sb.append(", addTime=").append(addTime);
        sb.append(", isException=").append(isException);
        sb.append(", exceptionName=").append(exceptionName);
        sb.append(", requestParams=").append(requestParams);
        sb.append(", exceptionMsg=").append(exceptionMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}