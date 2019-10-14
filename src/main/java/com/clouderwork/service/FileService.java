package com.clouderwork.service;


import com.clouderwork.contant.GlobalConstant;
import com.google.common.collect.Lists;
import net.coobird.thumbnailator.Thumbnails;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author : xuqiang
 * @Description : 文件操作service
 * @Date: 2017/7/24
 */
@Service
public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${apply.storage-root-path}")
    private String rootPath;

    @Value("${file.prefix}")
    private String filePre;

      /**
       * 保存图片文件
       * type 1图片 2音频 3其他文件
       * @param file MultipartFile
       * @return /file/temp/yyyymmdd/filename
       */
      public String saveFile(MultipartFile file,Double quality,int type) {
        if (!file.isEmpty()) {
          try {
            String fileName = CommonService.getRandomStr(10) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            Path path = store(file,
                    GlobalConstant.fileDirectory + File.separator + new DateTime().toString("yyyyMMdd"),
                    fileName,quality,type);
            return filePre+Paths.get(GlobalConstant.URL_PREFIX, path.toString().replace("\\", "/").replace(rootPath, ""))
                    .toString().replace("\\", "/");
          } catch (Exception e) {
            LOGGER.error("FileService.saveFile error : 上传失败！ ", e.getMessage());
            throw new IllegalArgumentException("上传失败！ " + e.getMessage());
          }
        } else {
          LOGGER.error("FileService.saveFile error : 文件不存在！ ");
          throw new IllegalArgumentException("文件不存在！");
        }
      }

      /**
       * 批量上传图片
       */
      public List<String> batchSaveImgFile(MultipartFile[] files, Double quality) {
        List<String> list = Lists.newArrayList();
        for(MultipartFile file : files){
          if (!file.isEmpty()) {
            try {
              String fileName = CommonService.getRandomStr(10) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
              Path path = store(file,
                      GlobalConstant.fileDirectory + File.separator + new DateTime().toString("yyyyMMdd"),
                      fileName,quality,1);
              list.add(Paths.get(GlobalConstant.URL_PREFIX, path.toString().replace("\\", "/").replace(rootPath, ""))
                      .toString().replace("\\", "/"));
            } catch (Exception e) {
              LOGGER.error("FileService.saveFile error : 上传失败！ ", e.getMessage());
              throw new IllegalArgumentException("上传失败！ " + e.getMessage());
            }
          }
        }
        return list;
      }

      /**
       * 根据数据库file url获取文件
       *
       * @param fileUrl /file/temp/yyyyMMdd/filename
       * @return File
       */
      public File loadFileByFileUrl(String fileUrl) throws Exception {
        Path file = Paths.get(rootPath, fileUrl);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
          return resource.getFile();
        } else {
          LOGGER.error("FileService.loadFileByFileUrl error : {} ", resource);
          throw new IllegalArgumentException("Could not read file: " + fileUrl);
        }
      }

        /**
         *
         * @param file
         * @param increasePath
         * @param saveFilename
         * @param quality
         * @param type 1 图片  2音频  3其他
         * @return
         */
        public Path store(MultipartFile file, String increasePath, String saveFilename,Double quality,int type) {
            saveFilename = StringUtils.hasText(saveFilename) ? saveFilename : file.getOriginalFilename();
            Path modifiedPath = Paths.get(rootPath, increasePath);
            if (!modifiedPath.toFile().exists()) {
                modifiedPath.toFile().mkdirs();
            }

            try {
                if (file.isEmpty()) {
                    LOGGER.error("FileService.store error : {} ", file);
                    throw new IllegalArgumentException("Failed to store empty file " + file.getOriginalFilename());
                }
                if(type == 1 ){
                    quality = quality>1?1.0:quality;
                    Thumbnails.of(file.getInputStream()).scale(1).outputQuality(quality).toFile(modifiedPath.resolve(saveFilename).toString());
                    //拼上宽高,便于app处理排版 文件名格式 原有文件名@宽像素数x高像素数
//                    BufferedImage image = ImageIO.read(file.getInputStream());
//                    if (image != null) {//如果image=null 表示上传的不是图片格式
//                        saveFilename = saveFilename+"?"+image.getWidth()+"x"+image.getHeight();
//                    }
                } else {
                    Files.copy(file.getInputStream(), modifiedPath.resolve(saveFilename));
                }
                return Paths.get(modifiedPath.toString(), saveFilename);
            } catch (IOException e) {
                LOGGER.error("FileService.store error : {} ", e);
                throw new IllegalArgumentException("Failed to store file " + file.getOriginalFilename(), e);
            }
        }

      /**
       * 根据数据库file url删除文件
       *
       * @param fileUrl /file/temp/yyyyMMdd/filename
       * @throws Exception
       */
      public void delByFileUrl(String fileUrl) throws Exception {
        if(fileUrl.contains("?")){
          fileUrl=fileUrl.substring(0,fileUrl.lastIndexOf("?"));
        }
        Path file = Paths.get(rootPath, fileUrl.replace(GlobalConstant.URL_PREFIX, ""));
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
          resource.getFile().delete();
        }
      }

      /**
       * 获取生成图片路径
       */
      public String genRandomJpgFilePath(){
        String fileName = CommonService.getRandomStr(10) .concat(".jpg");
        Path modifiedPath = Paths.get(rootPath, GlobalConstant.fileDirectory + File.separator + new DateTime().toString("yyyyMMdd"));
        if (!modifiedPath.toFile().exists()) {
          modifiedPath.toFile().mkdirs();
        }
        return Paths.get(modifiedPath.toString(), fileName).toString();
      }

      /**
       * 根据图片文件保存路径,返回rest 图片相对地址
       * withWidthHeight true 拼上宽高，false 不拼宽高
       */
      public String getRestPathByJpgFilePath(String jpgFilePath, Boolean withWidthHeight) throws Exception{
        File file = new File(jpgFilePath);
        if (file != null && withWidthHeight) {
          BufferedImage buffImg = ImageIO.read(file);
          if (buffImg != null) {//如果image=null 表示上传的不是图片格式
            jpgFilePath = jpgFilePath+"?"+buffImg.getWidth()+"x"+buffImg.getHeight();
          }
        } else if(file == null){
          throw new IllegalArgumentException("图片路径不正确!");
        }
        return Paths.get(GlobalConstant.URL_PREFIX, jpgFilePath.replace("\\", "/").replace(rootPath, ""))
                .toString().replace("\\", "/");
      }

      /**
       * 根据数据库存储的rest路径获取文件服务器存储的文件实际全路径
       */
      public String getfilePathByDatabaseRestURL(String restURL){
          if(restURL.contains("?")){
              restURL=restURL.substring(0,restURL.lastIndexOf("?"));
          }
          Path file = Paths.get(rootPath, restURL.replaceFirst(GlobalConstant.URL_PREFIX,""));
          return file.toString().replace("\\", "/");
      }
}
