package com.clouderwork.dao;

import com.clouderwork.pojo.TLabelArticle;
import com.clouderwork.pojo.TLabelArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLabelArticleMapper {
    long countByExample(TLabelArticleExample example);

    int deleteByExample(TLabelArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TLabelArticle record);

    int insertSelective(TLabelArticle record);

    List<TLabelArticle> selectByExample(TLabelArticleExample example);

    TLabelArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TLabelArticle record, @Param("example") TLabelArticleExample example);

    int updateByExample(@Param("record") TLabelArticle record, @Param("example") TLabelArticleExample example);

    int updateByPrimaryKeySelective(TLabelArticle record);

    int updateByPrimaryKey(TLabelArticle record);
}