package com.clouderwork.pojo.vo;

import com.clouderwork.pojo.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "菜单vo")
@Data
public class SysMenuVo implements TreeEntity<SysMenuVo> {

    @ApiModelProperty("菜单id")
    private Integer id;

    @ApiModelProperty("菜单编号")
    private String code;

    @ApiModelProperty("是否选中")
    private Boolean isChecked;

    @ApiModelProperty("菜单父id")
    private Integer pid;

    @ApiModelProperty("所有子菜单,子节点")
    private List<SysMenuVo> nodes;

    @ApiModelProperty("当前菜单的所有父菜单id,每个用中括号包含,逗号分隔")
    private String pids;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("url地址")
    private String menuUrl;

    @ApiModelProperty("菜单层级")
    private Integer levels;

    @ApiModelProperty("是否是菜单（1：是  0：否）")
    private Integer ismenu;

    @ApiModelProperty("菜单状态 : (1：启用  2：冻结、停用  3：删除")
    private Integer status;

    @ApiModelProperty("菜单排序号")
    private Integer sort;

    @ApiModelProperty("页面所对应的权限")
    private String btnPermissions;

    @ApiModelProperty("页面描述")
    private String menuDesc;

    @ApiModelProperty("添加时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date addTime;

    public void setChildList(List<SysMenuVo> childList) {
        this.nodes=childList;
    }
    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

}