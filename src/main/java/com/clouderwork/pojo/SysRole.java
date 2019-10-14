package com.clouderwork.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 状态(1：启用  2：冻结、停用  3：删除
     */
    private Integer status;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * sys_role
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 角色名称
     * @return role_name 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 角色描述
     * @return role_desc 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 角色描述
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * 状态(1：启用  2：冻结、停用  3：删除
     * @return status 状态(1：启用  2：冻结、停用  3：删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(1：启用  2：冻结、停用  3：删除
     * @param status 状态(1：启用  2：冻结、停用  3：删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return add_time 
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 
     * @param addTime 
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
        sb.append(", roleName=").append(roleName);
        sb.append(", roleDesc=").append(roleDesc);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}