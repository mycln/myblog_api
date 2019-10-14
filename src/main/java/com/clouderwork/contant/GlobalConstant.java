package com.clouderwork.contant;

/**
 * @Author : xuqiang
 * @Description : 常量类
 * @Date: 2017/7/19
 */
public class GlobalConstant {
	//cw_region表中国code
	public static final int chinaCode = 100000;

	//图片文件保存文件夹
	public static final String fileDirectory = "/temp";
	public static final String URL_PREFIX = "/file";

	//图片相对路径  分割
	public static final String PIC_SPLIT = ",";

	//一级分隔
	public static final String firstSplit = "#@#";

    //文件名分隔
    public static final String fileNameSplit = "_";

	//oss 文件上传的参数 accessKey
	public static final String endpoint="oss-cn-hangzhou.aliyuncs.com";
	//oss 上传图片 accessKey
	public static final String accessKeyId="LTAIPRQP2T3szu8D";
	//oss 上传图片 accessKeySecret
	public  static final String accessKeySecret="SO5Q0fHY4Af0dYRie2yUxJOYmr0ST2";
	//阿里短信配置信息
	public static final String signName="央广视讯";
	public static final String templateCode="SMS_146600004";

	//获取文件最后URL
	public static final String fileUlrPrefix="https://file.fansunion.com/";

	public static final String passStr = "api_2018_1.0.0";

	public static final String ret = "ret";

	//所有权限标记
	public static final String permStr = "all";
}