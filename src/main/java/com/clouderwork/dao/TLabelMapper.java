package com.clouderwork.dao;

import com.clouderwork.pojo.TLabel;
import com.clouderwork.pojo.TLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLabelMapper {
    long countByExample(TLabelExample example);

    int deleteByExample(TLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TLabel record);

    int insertSelective(TLabel record);

    List<TLabel> selectByExample(TLabelExample example);

    TLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TLabel record, @Param("example") TLabelExample example);

    int updateByExample(@Param("record") TLabel record, @Param("example") TLabelExample example);

    int updateByPrimaryKeySelective(TLabel record);

    int updateByPrimaryKey(TLabel record);
}