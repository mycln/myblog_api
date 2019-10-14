package com.clouderwork.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "验证码VO")
@Data
public class CodeVo implements Serializable {

    private static final long serialVersionUID = 2551758948357095732L;
    @ApiModelProperty("验证码比对的结果")
    private boolean checkCode;

    @ApiModelProperty("1:验证码有效！2:验证码失效")
    private Integer valid;

    @ApiModelProperty("验证码图片地址")
    private String codeUrl;

    @ApiModelProperty("随机key")
    private String randomKey;

}