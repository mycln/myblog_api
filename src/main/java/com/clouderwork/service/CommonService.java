package com.clouderwork.service;

import com.clouderwork.common.MD5;
import com.clouderwork.common.SCaptcha;
import com.clouderwork.contant.GlobalConstant;
import com.clouderwork.dao.SysUserMapper;
import com.clouderwork.enums.CodeEnum;
import com.clouderwork.enums.StatusEnum;
import com.clouderwork.pojo.SysUser;
import com.clouderwork.pojo.SysUserExample;
import com.clouderwork.pojo.vo.CodeVo;
import com.clouderwork.pojo.vo.LoginUser;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Author xuqiang
 * @Contact QQ、手机号或者云沃客账号
 * @Description
 * @Date Created in 2017/11/18
 */

@Service
public class CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	private RedisService redisService;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysService sysService;

	@Autowired
	private FileService fileService;

	@Autowired
	private  CommonService commonService;

	@Value("${oss.thumb.address}")
	private String thumb;

	@Value("${oss.zip.address}")
	private String zipUrl;

	@Value("${code.check}")
	private boolean isCheck;

	@Value("${code.capital.small}")
	private boolean isCapitalAndSmall;

	@Value("${file.prefix}")
	private String filePre;

	/**
	 * 获取当前登陆用户信息
	 */
	public LoginUser getCurreUser() {
		String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("token");
		if (StringUtils.isEmpty(token)) {
			throw new IllegalArgumentException("请登陆后再试!");
		}
		return (LoginUser) redisService.getObject(token);
	}

	public static String getRandomStr(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * hou登录操作,登录后保存相关数据到redis token 在redis默认保存10天
	 */
	public LoginUser backLogin(String username, String pass) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsernameEqualTo(username).andStatusNotEqualTo(StatusEnum.DEL.getValue());
		List<SysUser> users = sysUserMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users)) {
			throw new IllegalArgumentException("用户不存在!");
		}
		SysUser user = users.get(0);
		if (!user.getPassword().equalsIgnoreCase(CommonService.getPassMd5(pass,user.getSalt()))){
			throw new IllegalArgumentException("用户密码不正确!");
		}

		if (user != null && user.getStatus() == StatusEnum.UNUSE.getValue()) {
			throw new IllegalArgumentException("当前用户已冻结,请联系管理员!");
		}
		String tokenString = "back" + user.getId() + username + DateTime.now().toString("yyyyMMdd");
		String token = new MD5().getMD5ofStr(tokenString);
		// token和用户对象放到redis中
		LoginUser tu = new LoginUser();
		BeanUtils.copyProperties(user,tu);
		tu.setToken(token);
		tu.setUserMenus(sysService.userMenuList(user.getId()));
		redisService.setObject(token, tu);
		redisService.expire(token, 864000);// token 有效期10天
		return tu;
	}

	public void backLogout() {
		String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("token");
		redisService.delete(token);
	}

	/**
	 * 判断是否订单重复提交,重复提交的提示
	 *
	 * @param key
	 */
	public void checkSubmit(String key) {
		if (redisService.getStr(key) == null) {
			redisService.setStr(key, key);
			redisService.expire(key, 10);// 10秒内禁止重复提交
		} else if (redisService.getStr(key).equalsIgnoreCase(key)) {
			throw new IllegalArgumentException("请稍候......");
		}
	}

	public static boolean isNumber(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static Boolean isPassTrue(String rightStr, String inputStr, String salt) {
		MD5 m = new MD5();
		if (rightStr.equals(m.getMD5ofStr(m.getMD5ofStr(inputStr) + GlobalConstant.passStr + salt))) {
			return true;
		}
		return false;
	}

	public static String getPassMd5(String pass, String salt) {
		MD5 m = new MD5();
		return m.getMD5ofStr(m.getMD5ofStr(pass) + GlobalConstant.passStr + salt);
	}

	/**
	 * 生成验证码
	 * @param randomStr
	 * @return
	 * @throws Exception
     */
	public CodeVo createCode(String randomStr) throws Exception {
		CodeVo codeVo = new CodeVo();
		codeVo.setCheckCode(false);
		codeVo.setValid(CodeEnum.EFFECTIVE.getValue());
		//获取生成图片的地址
		String nameUrl = fileService.genRandomJpgFilePath();
		String restPathByJpgFilePath = fileService.getRestPathByJpgFilePath(nameUrl, false);
		saveImgPath(restPathByJpgFilePath);
		SCaptcha instance = new SCaptcha() ;
		instance.write(nameUrl);
		redisService.setStr(randomStr, instance.getCode());
		redisService.expire(randomStr, 300);
		codeVo.setCodeUrl(this.getFullImgPath(restPathByJpgFilePath));
		codeVo.setRandomKey(randomStr);
		return codeVo;
	}

	private void saveImgPath(String url){
		String str = redisService.getStr("tmpImgStrs");
		str=StringUtils.isEmpty(str)?url:str+GlobalConstant.PIC_SPLIT+url;
		redisService.setStr("tmpImgStrs",str);
	}

	public void delImgs(){
		String str = redisService.getStr("tmpImgStrs");
		if(!StringUtils.isEmpty(str)){
			for(String img:str.split(GlobalConstant.PIC_SPLIT)){
				try {
					fileService.delByFileUrl(img);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 检验验证码输入的是否正确
	 * @param randomKey
	 * @param userCode
	 * @return
	 * @throws Exception
     */
	public CodeVo checkCode(String randomKey, String userCode) throws Exception {
		CodeVo codeVo = new CodeVo();
		//验证验证码输入的不合法
		if (StringUtils.isEmpty(userCode) || userCode.length() != 4) {
			codeVo.setCheckCode(false);
			codeVo.setValid(CodeEnum.FAILURE.getValue());
			redisService.delete(randomKey);
			return codeVo;
		}
		//不开启验证码验证 直接返回true
		if (!isCheck) {
			codeVo.setCheckCode(true);
			codeVo.setValid(CodeEnum.EFFECTIVE.getValue());
			redisService.delete(randomKey);
			return codeVo;
		}
		String rightCode = redisService.getStr(randomKey);
		if (rightCode != null) {
			codeVo.setValid(CodeEnum.EFFECTIVE.getValue());
			if (!isCapitalAndSmall) { //不区分大小写
				if (rightCode.equalsIgnoreCase(userCode)) {
					codeVo.setCheckCode(true);
				}else{
					String newRandomStr = commonService.getRandomStr(18);
					codeVo = this.createCode(newRandomStr);
				}
			} else {//区分大小写
				if (rightCode.equals(userCode)) {
					codeVo.setCheckCode(true);
				}else{
					String newRandomStr = commonService.getRandomStr(18);
					codeVo = this.createCode(newRandomStr);
				}
			}
		}else{
			String newRandomStr = getRandomStr(18);
			codeVo = createCode(newRandomStr);
			codeVo.setValid(CodeEnum.FAILURE.getValue());
		}
		if(codeVo.isCheckCode()){
			String key = randomKey+GlobalConstant.ret;
			redisService.setStr(key,"true");
			redisService.expire(key,300);
		}
		redisService.delete(randomKey);
		return codeVo;
	}



	/**
	 *  副本编辑上传图片接口
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	public HashMap richtextImgUploadAdds(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		HashMap resultMap = Maps.newHashMap();
		String url = fileService.saveFile(file,0.6,1);
		if(StringUtils.isEmpty(url)){
			resultMap.put("success",false);
			resultMap.put("msg","上传失败");
			return resultMap;
		}
		resultMap.put("success",true);
		resultMap.put("msg","上传成功");
		resultMap.put("file_path",url);
		response.addHeader("Access-Control-Allow-Headers","X-File-Name");
		return resultMap;
	}

	/**
	 * 获取保存到数据库的图片地址
	 * @param fullImgPath
	 * @return
     */
	public String getSaveImgPath(String fullImgPath){
		if (!StringUtils.isEmpty(fullImgPath)){
			return fullImgPath.replace(filePre,"");
		}
		return "";
	}

	/**
	 * 获取图片展示完整URL地址
	 * @param imgPath 数据库图片保存相对地址
	 * @return
	 */
	public String getFullImgPath(String imgPath){
		if (!StringUtils.isEmpty(imgPath) && !imgPath.startsWith("http")){
			return filePre + imgPath;
		}
		return imgPath;
	}

	/**
	 *  文件下载
	 * @param urlStr 真实url
	 * @param fileName 文件名字
	 * @param response
	 * @throws IOException
	 */
	public void  downLoadFromUrl(String urlStr,String fileName,HttpServletResponse response) throws IOException {
		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置超时间为3秒
		conn.setConnectTimeout(3*1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//得到输入流
		InputStream inputStream = conn.getInputStream();
		//获取自己数组
		byte[] getData = readInputStream(inputStream);

//		if(getData.length<1){
//			throw new IllegalArgumentException("该文件内容为空......");
//		}
		OutputStream os = response.getOutputStream();
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
		os.write(getData);
		if(os!=null){
			os.close();
		}
		if(inputStream!=null){
			inputStream.close();
		}

	}
	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public  byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
}
