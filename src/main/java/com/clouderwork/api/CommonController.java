package com.clouderwork.api;

import com.clouderwork.common.CommResult;
import com.clouderwork.pojo.vo.CodeVo;
import com.clouderwork.pojo.vo.LoginUser;
import com.clouderwork.service.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author xuqiang
 * @Contact
 * @Description
 * @Date Created in 2018/4/20
 */
@CrossOrigin(origins = "*")
@RestController
@Api(description = "C01 公用接口")
@RequestMapping(value = "/common")
public class CommonController {
	private static Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private FileService fileService;

	@Autowired
	private OSSService ossService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private SmsService smsService;

	@ApiOperation(value = "C0101 后台登录接口 状态：已完成", response = CommResult.class)
	@ApiResponses({
			@ApiResponse(code=200,message="成功,返回content中vo类参数如下",response= LoginUser.class)
	})
	@PostMapping(value = "/back/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult backLogin(
			@ApiParam(value = "用户名", required = true) @RequestParam(value = "username") String username,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "pass") String pass) {
		return CommResult.ok(commonService.backLogin(username, pass));
	}

	@ApiOperation(value = "C0102 登出接口 状态：已完成", response = CommResult.class)
	@PostMapping(value = "/back/loginout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult backLoginOut() {
		commonService.backLogout();
		return CommResult.ok();
	}
//
//
//	@ApiOperation(value = "C0103 oss图片及音频上传 状态：已完成",
//			notes = "返回阿里云全路径; 缩略图访问形式为返回路径拼上 ?x-oss-process=style/50h 其中50h为阿里云创建的样式名字", response = CommResult.class)
//	@PostMapping(value = "/oss/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public CommResult uploadAdd(
//			@ApiParam(value = "图片文件", required = true) @RequestParam(value = "file") MultipartFile file,
//			@ApiParam(value = "1拼上宽高 0不拼宽高") @RequestParam(value = "withWH",required = false) Integer withWH) {
//		Boolean withWidthHeight = withWH==null?false:(withWH==1?true:false);
//		return CommResult.ok(ossService.saveFile(file,withWidthHeight));
//	}
//
//	@ApiOperation(value = "C0104 oss图片删除 状态：已完成", notes = "单个图片删除,参数格式为上传图片时的返回地址", response = CommResult.class)
//	@PostMapping(value = "/oss/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public CommResult delUploadFile(@ApiParam(value = "图片文件地址", required = true) @RequestParam(value = "fileUrl") String fileUrl) throws Exception {
//		ossService.delFile(fileUrl);
//		return CommResult.ok();
//	}

	@ApiOperation(value = "C0105 文件上传 状态：已完成", notes = "单个图片上传,返回图片访问全路径URL地址", response = CommResult.class)
	@PostMapping(value = "/upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult uploadFile(
			@ApiParam(value = "文件", required = true) @RequestParam(value = "file") MultipartFile file,
			@ApiParam(value = "1图片 2音频 3其他文件") @RequestParam(value = "type",defaultValue = "1",required = false) Integer type) {
		type = type==null?1:type;
		return CommResult.ok(fileService.saveFile(file,0.6,type));
	}

	@ApiOperation(value = "C0106 文件删除 状态：已完成", response = CommResult.class)
	@PostMapping(value = "/del/file", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult delFile(
			@ApiParam(value = "文件地址 相对地址", required = true) @RequestParam(value = "fileURL") String fileURL) throws Exception{
		fileService.delByFileUrl(fileURL);
		return CommResult.ok();
	}

	@ApiOperation(value = "C0107 生成随机验证码图片 状态：已完成",notes = "验证码的有效期为300秒",response = CommResult.class)
	@ApiResponses({
			@ApiResponse(code=200,message="成功,返回content中vo类参数如下",response=CodeVo.class)
	})
	@GetMapping(value = "/create/code", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult createCode() throws Exception{
		String randomStr = commonService.getRandomStr(18);
		return CommResult.ok(commonService.createCode(randomStr));
	}

	@ApiOperation(value = "C0108 检验输入验证码 状态：已完成",
			notes = "当CodeVo中checkCode为false 会再发返回新的验证码,isPast 代表上次请求验证码的状态",
			response = CommResult.class)
	@ApiResponses({
			@ApiResponse(code=200,message="成功,返回content中vo类参数如下",response=CodeVo.class)
	})
	@PostMapping(value = "/check/code", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult checkCode(
			@ApiParam(value = "生成验证码接口返回的randomKey", required = true) @RequestParam(value = "randomKey") String randomKey,
	        @ApiParam(value = "用户输入的验证码", required = true) @RequestParam(value = "userCode") String userCode) throws Exception {
		return CommResult.ok(commonService.checkCode(randomKey,userCode));
	}

	@ApiOperation(value = "C0110 根据code获取下属省市区 状态：已完成", notes = "code 100000取全部省信息",response = CommResult.class)
	@GetMapping(value = "/area/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult arealist(@ApiParam(value = "地域code",required = true)@RequestParam(value = "code") int code) {
		return CommResult.ok(regionService.queryByParentCode(code));
	}

	@ApiOperation(value = "C0111 发送验证码 状态：已完成", response = CommResult.class)
	@PostMapping(value = "/sms/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommResult sendsms(@ApiParam(value = "手机号",required = true)@RequestParam(value = "phone") String phone) {
		try {
			String ret = smsService.sendMessage(phone);
			if("ok".equalsIgnoreCase(ret)){
				return CommResult.ok("短信发送成功!");
			}else{
				return CommResult.error("短信发送失败!");
			}
		} catch (Exception e) {
			logger.error("发送验证码器异常", e);
			e.printStackTrace();
		}
		return CommResult.error("发送失败");
	}


	@ApiOperation(value = "C0112 下载文件接口 状态:已完成", response = CommResult.class)
	@GetMapping(value = "/download/file")
	public CommResult downloadFile(
			@ApiParam(value = "文件url",required = true) @RequestParam(value = "fileUrl") String fileUrl,
			@ApiParam(value = "文件保存名字",required = true) @RequestParam(value = "realName") String realName,
			HttpServletResponse response) throws  Exception{
		commonService.downLoadFromUrl(fileUrl,realName,response);
		return CommResult.ok();
	}
}
