package com.clouderwork.pojo.entity;

import java.util.List;

/**
 * Author: xuqiang
 * Description:树形数据实体接口
 */
public interface TreeEntity<E> {
    public Integer getId();
    public Integer getPid();
    public void setChildList(List<E> childList);
}
