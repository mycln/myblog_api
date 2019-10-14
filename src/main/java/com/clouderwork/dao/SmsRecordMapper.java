package com.clouderwork.dao;

import com.clouderwork.pojo.SmsRecord;
import com.clouderwork.pojo.SmsRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface SmsRecordMapper {
    long countByExample(SmsRecordExample example);

    int deleteByExample(SmsRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsRecord record);

    int insertSelective(SmsRecord record);

    List<SmsRecord> selectByExample(SmsRecordExample example);

    SmsRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsRecord record, @Param("example") SmsRecordExample example);

    int updateByExample(@Param("record") SmsRecord record, @Param("example") SmsRecordExample example);

    int updateByPrimaryKeySelective(SmsRecord record);

    int updateByPrimaryKey(SmsRecord record);
}