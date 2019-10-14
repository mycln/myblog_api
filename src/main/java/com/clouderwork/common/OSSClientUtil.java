package com.clouderwork.common;

/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/7/19
 */

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.clouderwork.contant.GlobalConstant;
import com.clouderwork.service.CommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云 OSS文件类
 *
 * @author YuanDuDu
 */
@Component
public class OSSClientUtil {
    Log log = LogFactory.getLog(OSSClientUtil.class);

    @Value("${oss.bucketName}")
    private String bucketName;
    //文件存储目录
    @Value("${oss.filedir}")
    private String filedir;


    private OSSClient ossClient;

    public OSSClientUtil() {
        ossClient = new OSSClient(GlobalConstant.endpoint, GlobalConstant.accessKeyId, GlobalConstant.accessKeySecret);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(GlobalConstant.endpoint, GlobalConstant.accessKeyId, GlobalConstant.accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传图片
     *
     * @param url
     */
    public void uploadImg2Oss(String url) {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2OSS(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("图片上传失败");
        }
    }


    public String uploadImg2Oss(MultipartFile file, Boolean withWidthHeight) {
        String originalFilename = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            String key = this.uploadFile2OSS(inputStream, CommonService.getRandomStr(10)
                    + originalFilename.substring(originalFilename.lastIndexOf(".")));
            if (withWidthHeight) {
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image != null) {//如果image=null 表示上传的不是图片格式
                    key = key + "?" + image.getWidth() + "x" + image.getHeight();
                }
            }
            return GlobalConstant.fileUlrPrefix + key;
        } catch (Exception e) {
            throw new IllegalArgumentException("图片上传失败");
        }
    }

    public String uploadImg2Oss(File file) {
        String originalFilename = file.getName();
        try {
            String key = this.uploadFile2OSS(file, CommonService.getRandomStr(10)
                    + originalFilename.substring(originalFilename.lastIndexOf(".")));
            return GlobalConstant.fileUlrPrefix+ key;
        } catch (Exception e) {
            throw new IllegalArgumentException("图片上传失败");
        }
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 文件路径key
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            String key = filedir + new DateTime().toString("yyyyMMdd") + File.separator + fileName;
            ossClient.putObject(bucketName, key, instream, objectMetadata);
            ret = key;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public String uploadFile2OSS(File file, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.length());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            String key = filedir + new DateTime().toString("yyyyMMdd") + File.separator + fileName;
            ossClient.putObject(bucketName, key, file, objectMetadata);
            ret = key;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equalsIgnoreCase(".mp3") || FilenameExtension.equalsIgnoreCase(".m4a")) {
            return "Audio/mpeg";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 50);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


    /**
     * 根据key删除OSS服务器上的文件
     */
    public void deleteFile(String URL) {
        if (!StringUtils.isEmpty(URL) && URL.contains(filedir)) {
            String key = URL.split("\\?")[0].split(filedir)[1];
            ossClient.deleteObject(bucketName, filedir + key);
        }
    }
}