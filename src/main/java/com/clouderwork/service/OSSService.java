package com.clouderwork.service;

import com.clouderwork.common.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/7/19
 */
@Service
public class OSSService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OSSService.class);

    @Autowired
    private OSSClientUtil ossClient;


    @Autowired
    private FileService fileService;

    @Value("${project.url}")
    private String preUrl;

    @Value("${oss.thumb.address}")
    private String thumb;

    @Value("${oss.zip.address}")
    private String zipUrl;

    public String saveFile(MultipartFile file,Boolean withWidthHeight)  {
        if (!file.isEmpty()) {
            return ossClient.uploadImg2Oss(file,withWidthHeight);
        } else {
            LOGGER.error("FileService.saveFile error : 文件不存在！");
            throw new IllegalArgumentException("文件不存在！");
        }
    }

    public void delFile(String fileURL)  {
        try {
            ossClient.deleteFile(fileURL);
        } catch (Exception e) {
            LOGGER.error("FileService.saveFile error : 上传失败！", e.getMessage());
            throw new IllegalArgumentException("上传失败！ " + e.getMessage());
        }
    }

    /**
     * 获取oss压缩图片路径
     * @param url
     * @return
     */
    public String getZipUrl(String url){
        if(url.contains("?")){
            return url+zipUrl.replace("?","&");
        }
        return url+zipUrl;
    }

    /**
     * 获取oss缩略图路径
     * @param url
     * @return
     */
    public String getThumbUrl(String url){
        if(url.contains("?")){
            return url+thumb.replace("?","&");
        }
        return url+thumb;
    }
}
