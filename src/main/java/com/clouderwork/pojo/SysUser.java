package com.clouderwork.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态(1：启用  2：冻结、停用  3：删除）
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * sys_user
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     * @return id 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账号
     * @return username 账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 账号
     * @param username 账号
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * md5密码盐
     * @return salt md5密码盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * md5密码盐
     * @param salt md5密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 头像地址
     * @return avatar 头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像地址
     * @param avatar 头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 电话
     * @return phone 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 状态(1：启用  2：冻结、停用  3：删除）
     * @return status 状态(1：启用  2：冻结、停用  3：删除）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(1：启用  2：冻结、停用  3：删除）
     * @param status 状态(1：启用  2：冻结、停用  3：删除）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 添加时间
     * @return add_time 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 添加时间
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", avatar=").append(avatar);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}