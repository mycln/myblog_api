package com.clouderwork.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : ChangLina
 * @Description : StorageProperties
 * @Date: Created by 下午10:22 on 2017/10/19.
 * @Modified By :
 */
@Component
@ConfigurationProperties(prefix = "apply")
public class StorageProperties {

    //上传存储路径
    private String storageRootPath;

	public String getStorageRootPath() {
		return storageRootPath;
	}
	
	public void setStorageRootPath(String storageRootPath) {
		this.storageRootPath = storageRootPath;
	};
}
