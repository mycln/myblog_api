package com.clouderwork.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "登录返回信息")
@Data
public class LoginUser {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("当前用户分配的菜单集合")
    private List<SysMenuVo> userMenus;

    @ApiModelProperty("头像地址")
    private String avatar;

    @ApiModelProperty("电话")
    private String phone;

}