package com.clouderwork.pojo.vo.token;

import java.util.List;

public class TokenUser {

    /**
     * 后台用户id
     */
    private Long id;

    /**
     * 后台用户名
     */
    private String username;

    /**
     * 当前用户分配的角色id
     */
    private List<Integer> userRoleIds;

    /**
     * 当前用户分配的菜单id集合
     */
    private List<Integer> userMenuIds;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * md5密码盐
     */
    private String salt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(List<Integer> userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public List<Integer> getUserMenuIds() {
        return userMenuIds;
    }

    public void setUserMenuIds(List<Integer> userMenuIds) {
        this.userMenuIds = userMenuIds;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}