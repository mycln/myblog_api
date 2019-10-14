package com.clouderwork.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@ApiModel(value = "日志信息类")
@Component
@Data
public class SysLogsVo implements Serializable {
    @ApiModelProperty("日志信息id")
    private Long id;


    @ApiModelProperty(value = "请求controller的名称")
    private String className;


    @ApiModelProperty(value = "请求controller的方法")
    private String method;


    @ApiModelProperty(value = "http请求content-type")
    private String contentType;

    @ApiModelProperty(value = "http请求类型")
    private String requestType;


    @ApiModelProperty(value = "请求方法的描述")
    private String description;


    @ApiModelProperty(value = "请求接口地址")
    private String serverAddr;


    @ApiModelProperty(value = "发出请求的主机的 IP 地址")
    private String remoteAddr;


    @ApiModelProperty(value = "请求来源使用的设备")
    private String deviceName;


    @ApiModelProperty(value = "请求来源使用的浏览器")
    private String browserName;


    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    @ApiModelProperty(value = "请求方法的路径")
    private String requestUri;

    @ApiModelProperty(value = " 用户是否登录")
    private Integer isLogin;


    @ApiModelProperty(value = "用户的id")
    private Long userId;


    @ApiModelProperty(value = "日志添加的时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date addTime;


    @ApiModelProperty(value = "是否异常 0:无异常；1发生异常")
    private Integer isException;


    @ApiModelProperty(value = "异常发生的类的名称")
    private String exceptionName;


    @ApiModelProperty(value = "请求参数")
    private String requestParams;


    @ApiModelProperty(value = "异常信息")
    private String exceptionMsg;

    /**
     * sys_logs
     */
    private static final long serialVersionUID = 1L;

}