package com.clouderwork.common;

import com.clouderwork.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "公用返回vo类")
public class CommResult {

	@ApiModelProperty("0成功 1失败 401未登录或无权限")
	public int code;

	@ApiModelProperty("code为1时为错误信息内容")
	public String message;

	@ApiModelProperty("返回对象内容")
	public Object content;

	public CommResult() {
		this.code = ResultCodeEnum.OK.getValue();
		this.message = ResultCodeEnum.OK.getText();
	}

	public CommResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public CommResult(int code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public static CommResult ok(){
		return new CommResult(ResultCodeEnum.OK.getValue(),ResultCodeEnum.OK.getText(),"");
    }

    public static CommResult ok(Object content){
        return new CommResult(ResultCodeEnum.OK.getValue(),ResultCodeEnum.OK.getText(),content);
    }

    public static CommResult error(String errormsg){
        return new CommResult(ResultCodeEnum.ERROR.getValue(),errormsg);
    }

    public static CommResult error(int code, String errormsg, Object content){
        return new CommResult(code,errormsg,content);
    }

	public Object getContent() {
		return content;
	}

//	public void setContent(List<T> content) {
//		this.content = content;
//	}

	public CommResult setContent(Object content){
		this.content = content;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}