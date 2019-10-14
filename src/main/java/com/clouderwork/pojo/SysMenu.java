package com.clouderwork.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单父id 一级菜单父id为0
     */
    private Integer pid;

    /**
     * 当前菜单的所有父菜单id,逗号分隔，前端使用，选择子菜单权限，由此自动选中所有父菜单
     */
    private String pids;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * url地址
     */
    private String menuUrl;

    /**
     * 菜单排序号
     */
    private Integer sort;

    /**
     * 菜单层级
     */
    private Integer levels;

    /**
     * 是否是菜单（1：是  0：否）
     */
    private Integer ismenu;

    /**
     * 备注
     */
    private String menuDesc;

    /**
     * 菜单状态 : (1：启用  2：冻结、停用  3：删除
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * sys_menu
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     * @return id 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 菜单编号
     * @return code 菜单编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 菜单编号
     * @param code 菜单编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 菜单父id 一级菜单父id为0
     * @return pid 菜单父id 一级菜单父id为0
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 菜单父id 一级菜单父id为0
     * @param pid 菜单父id 一级菜单父id为0
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 当前菜单的所有父菜单id,逗号分隔，前端使用，选择子菜单权限，由此自动选中所有父菜单
     * @return pids 当前菜单的所有父菜单id,逗号分隔，前端使用，选择子菜单权限，由此自动选中所有父菜单
     */
    public String getPids() {
        return pids;
    }

    /**
     * 当前菜单的所有父菜单id,逗号分隔，前端使用，选择子菜单权限，由此自动选中所有父菜单
     * @param pids 当前菜单的所有父菜单id,逗号分隔，前端使用，选择子菜单权限，由此自动选中所有父菜单
     */
    public void setPids(String pids) {
        this.pids = pids == null ? null : pids.trim();
    }

    /**
     * 菜单名称
     * @return menu_name 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 菜单图标
     * @return menu_icon 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 菜单图标
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * url地址
     * @return menu_url url地址
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * url地址
     * @param menuUrl url地址
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 菜单排序号
     * @return sort 菜单排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 菜单排序号
     * @param sort 菜单排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 菜单层级
     * @return levels 菜单层级
     */
    public Integer getLevels() {
        return levels;
    }

    /**
     * 菜单层级
     * @param levels 菜单层级
     */
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    /**
     * 是否是菜单（1：是  0：否）
     * @return ismenu 是否是菜单（1：是  0：否）
     */
    public Integer getIsmenu() {
        return ismenu;
    }

    /**
     * 是否是菜单（1：是  0：否）
     * @param ismenu 是否是菜单（1：是  0：否）
     */
    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    /**
     * 备注
     * @return menu_desc 备注
     */
    public String getMenuDesc() {
        return menuDesc;
    }

    /**
     * 备注
     * @param menuDesc 备注
     */
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc == null ? null : menuDesc.trim();
    }

    /**
     * 菜单状态 : (1：启用  2：冻结、停用  3：删除
     * @return status 菜单状态 : (1：启用  2：冻结、停用  3：删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 菜单状态 : (1：启用  2：冻结、停用  3：删除
     * @param status 菜单状态 : (1：启用  2：冻结、停用  3：删除
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", pid=").append(pid);
        sb.append(", pids=").append(pids);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", sort=").append(sort);
        sb.append(", levels=").append(levels);
        sb.append(", ismenu=").append(ismenu);
        sb.append(", menuDesc=").append(menuDesc);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}