package com.clouderwork.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "角色vo")
@Data
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = -2175405040589471415L;

    @ApiModelProperty("角色id")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    private String roleDesc;

    /**
     * 状态(1：启用  2：冻结、停用  3：删除
     */
    @ApiModelProperty("状态 1：启用  2：冻结、停用  3：删除")
    private Integer status;

    /**
     * 
     */
    @ApiModelProperty("添加时间")
    private Date addTime;

    /**
     * 
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}