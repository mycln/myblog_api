package com.clouderwork.dao;

import com.clouderwork.pojo.TComments;
import com.clouderwork.pojo.TCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCommentsMapper {
    long countByExample(TCommentsExample example);

    int deleteByExample(TCommentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TComments record);

    int insertSelective(TComments record);

    List<TComments> selectByExampleWithBLOBs(TCommentsExample example);

    List<TComments> selectByExample(TCommentsExample example);

    TComments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TComments record, @Param("example") TCommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") TComments record, @Param("example") TCommentsExample example);

    int updateByExample(@Param("record") TComments record, @Param("example") TCommentsExample example);

    int updateByPrimaryKeySelective(TComments record);

    int updateByPrimaryKeyWithBLOBs(TComments record);

    int updateByPrimaryKey(TComments record);
}