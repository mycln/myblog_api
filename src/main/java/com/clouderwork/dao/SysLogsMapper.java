package com.clouderwork.dao;

import com.clouderwork.pojo.SysLogs;
import com.clouderwork.pojo.SysLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SysLogsMapper {
    long countByExample(SysLogsExample example);

    int deleteByExample(SysLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLogs record);

    int insertSelective(SysLogs record);

    List<SysLogs> selectByExampleWithBLOBs(SysLogsExample example);

    List<SysLogs> selectByExample(SysLogsExample example);

    SysLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLogs record, @Param("example") SysLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") SysLogs record, @Param("example") SysLogsExample example);

    int updateByExample(@Param("record") SysLogs record, @Param("example") SysLogsExample example);

    int updateByPrimaryKeySelective(SysLogs record);

    int updateByPrimaryKeyWithBLOBs(SysLogs record);

    int updateByPrimaryKey(SysLogs record);
}