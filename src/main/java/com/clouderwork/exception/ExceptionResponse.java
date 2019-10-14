package com.clouderwork.exception;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ExceptionResponse implements Serializable{

    private static final long serialVersionUID = 1268256980395349306L;
    
    /**
     * 时间戳
     */
    private String timestamp = Instant.now().atZone(ZoneId.of("+08")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 错误标识
     */
    private String error;

    /**
     * 信息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    public ExceptionResponse(String  timestamp, Integer status, String error, String message, boolean success) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.success = success;
    }

    public ExceptionResponse(Integer status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

  public ExceptionResponse(Integer status, String error, String message, boolean success) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.success = success;
  }

  public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
