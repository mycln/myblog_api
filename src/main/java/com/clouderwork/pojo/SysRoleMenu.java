package com.clouderwork.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysRoleMenu implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单id
     */
    private Integer menuid;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * sys_role_menu
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 菜单id
     * @return menuid 菜单id
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * 菜单id
     * @param menuid 菜单id
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * 角色id
     * @return roleid 角色id
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 角色id
     * @param roleid 角色id
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuid=").append(menuid);
        sb.append(", roleid=").append(roleid);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}