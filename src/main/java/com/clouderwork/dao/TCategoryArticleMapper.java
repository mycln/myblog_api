package com.clouderwork.dao;

import com.clouderwork.pojo.TCategoryArticle;
import com.clouderwork.pojo.TCategoryArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCategoryArticleMapper {
    long countByExample(TCategoryArticleExample example);

    int deleteByExample(TCategoryArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCategoryArticle record);

    int insertSelective(TCategoryArticle record);

    List<TCategoryArticle> selectByExample(TCategoryArticleExample example);

    TCategoryArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCategoryArticle record, @Param("example") TCategoryArticleExample example);

    int updateByExample(@Param("record") TCategoryArticle record, @Param("example") TCategoryArticleExample example);

    int updateByPrimaryKeySelective(TCategoryArticle record);

    int updateByPrimaryKey(TCategoryArticle record);
}