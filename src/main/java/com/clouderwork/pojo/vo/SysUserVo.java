package com.clouderwork.pojo.vo;

import com.clouderwork.pojo.SysRole;
import com.clouderwork.pojo.SysUser;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value = "用户vo")
@Data
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = -8404299960370154461L;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * md5密码盐
     */
    @ApiModelProperty("md5密码盐")
    private String salt;

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    private String avatar;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;

    /**
     * 状态(1：启用  2：冻结、停用  3：删除）
     */
    @ApiModelProperty("状态(1：启用  2：冻结、停用  3：删除）")
    private Integer status;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    private Date addTime;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色列表")
    private List<SysRole> SysRole;
}