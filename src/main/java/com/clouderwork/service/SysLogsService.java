package com.clouderwork.service;

import com.clouderwork.common.IDGenerator;
import com.clouderwork.dao.SysLogsMapper;
import com.clouderwork.pojo.SysLogs;
import com.clouderwork.pojo.SysLogsExample;
import com.clouderwork.pojo.vo.SysLogsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/11/20
 */

@Service
public class SysLogsService {

	private static final Logger logger = LoggerFactory.getLogger(SysLogsService.class);

    @Autowired
    private SysLogsMapper sysLogsMapper;


    @Transactional(propagation= Propagation.REQUIRES_NEW)
	public void add(SysLogsVo sysLogsVo){
        SysLogs sysLogs = new SysLogs();
        BeanUtils.copyProperties(sysLogsVo,sysLogs);
        sysLogs.setId(IDGenerator.getRandomID());
        sysLogsMapper.insertSelective(sysLogs);
	}

    public PageInfo<SysLogsVo>list(String remoteAddr, Integer isLogin, String addTime, Integer pageNum, Integer pageSize) throws ParseException {
	    PageHelper.startPage(pageNum,pageSize);
        SysLogsExample example = new SysLogsExample();
        SysLogsExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(remoteAddr)){
            criteria.andRemoteAddrLike("%"+remoteAddr+"%");
        }
        if(isLogin!=null){
            criteria.andIsLoginEqualTo(isLogin);
        }
        if(!StringUtils.isEmpty(addTime)) {
            addTime = addTime +" 23:59:59";
            Date time = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(addTime).toDate();
            criteria.andAddTimeLessThanOrEqualTo(time);
        }
        example.setOrderByClause("add_time desc");
        List<SysLogs> sysLogs =   sysLogsMapper.selectByExampleWithBLOBs(example);
        PageInfo<SysLogs> pageInfo = new PageInfo<SysLogs>(sysLogs);
        LinkedList<SysLogsVo> list = Lists.newLinkedList();
        for(SysLogs s : pageInfo.getList()){
            SysLogsVo vo = new SysLogsVo();
            BeanUtils.copyProperties(s,vo);
            list.add(vo);
        }
        PageInfo<SysLogsVo> ret = new PageInfo<SysLogsVo>(list);
        BeanUtils.copyProperties(pageInfo,ret);
        ret.setList(list);
        return  ret;
    }
}
