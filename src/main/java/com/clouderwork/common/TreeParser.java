package com.clouderwork.common;


import com.clouderwork.pojo.entity.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: IrisNew
 * Description:解析树形数据工具类
 * Date: 2018/1/16 17:16
 */
public class TreeParser{
    /**
     * 解析树形数据
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(Integer topId, List<E> entityList) {
        List<E> resultList=new ArrayList<>();

        //获取顶层元素集合
        Integer parentId;
        for (E entity : entityList) {
            parentId=entity.getPid();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(),entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     */
    private  static  <E extends TreeEntity<E>>  List<E> getSubList(Integer id, List<E> entityList) {
        List<E> childList=new ArrayList<>();
        Integer parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getPid();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

        //递归退出条件
        if(childList.size()==0){
            return null;
        }

        return childList;
    }
}
