package com.clouderwork.pojo;

import java.io.Serializable;
import java.util.Date;

public class SmsRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 接收者手机号
     */
    private String receivePhone;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 1成功  0失败
     */
    private Integer success;

    /**
     * 状态码的描述
     */
    private String codeDesc;

    /**
     * 发送回执ID
     */
    private String bizid;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * sms_record
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
     * 接收者手机号
     * @return receive_phone 接收者手机号
     */
    public String getReceivePhone() {
        return receivePhone;
    }

    /**
     * 接收者手机号
     * @param receivePhone 接收者手机号
     */
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    /**
     * 短信内容
     * @return content 短信内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 短信内容
     * @param content 短信内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 1成功  0失败
     * @return success 1成功  0失败
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * 1成功  0失败
     * @param success 1成功  0失败
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

    /**
     * 状态码的描述
     * @return code_desc 状态码的描述
     */
    public String getCodeDesc() {
        return codeDesc;
    }

    /**
     * 状态码的描述
     * @param codeDesc 状态码的描述
     */
    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc == null ? null : codeDesc.trim();
    }

    /**
     * 发送回执ID
     * @return bizid 发送回执ID
     */
    public String getBizid() {
        return bizid;
    }

    /**
     * 发送回执ID
     * @param bizid 发送回执ID
     */
    public void setBizid(String bizid) {
        this.bizid = bizid == null ? null : bizid.trim();
    }

    /**
     * 发送时间
     * @return send_time 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", content=").append(content);
        sb.append(", success=").append(success);
        sb.append(", codeDesc=").append(codeDesc);
        sb.append(", bizid=").append(bizid);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}