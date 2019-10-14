package com.clouderwork.service;

import com.clouderwork.common.IDGenerator;
import com.clouderwork.dao.SysLogsMapper;
import com.clouderwork.dao.SysMenuMapper;
import com.clouderwork.enums.StatusEnum;
import com.clouderwork.params.SysMenuParams;
import com.clouderwork.pojo.SysLogs;
import com.clouderwork.pojo.SysLogsExample;
import com.clouderwork.pojo.SysMenu;
import com.clouderwork.pojo.SysMenuExample;
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
public class MenusService {

	private static final Logger logger = LoggerFactory.getLogger(MenusService.class);

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 菜单添加和修改的方法
     * @param msg
     */
    @Transactional
    public void add(SysMenuParams msg) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(msg,sysMenu);
        if(msg.getId()!= null){
            sysMenu.setId(msg.getId());
            sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
            logger.info("修改菜单信息{}",sysMenu);
        }else{
            sysMenu.setAddTime(new Date());
            sysMenuMapper.insertSelective(sysMenu);
            logger.info("添加菜单信息{}",sysMenu);
        }
    }

    @Transactional
    public void del(Integer id) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id);
        sysMenu.setStatus(StatusEnum.DEL.getValue());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        logger.info("删除菜单信息{}",sysMenu);
    }
}
