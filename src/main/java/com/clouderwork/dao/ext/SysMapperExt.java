package com.clouderwork.dao.ext;

import com.clouderwork.pojo.vo.SysMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMapperExt {
    
    /**
     * 获取指定用户的权限列表
     * @param userid
     * @return
     */
    List<SysMenuVo> getUserMenuList(@Param("userid") Long userid);

}