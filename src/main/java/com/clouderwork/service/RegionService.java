package com.clouderwork.service;


import com.clouderwork.contant.GlobalConstant;
import com.clouderwork.dao.SysRegionMapper;
import com.clouderwork.pojo.SysRegion;
import com.clouderwork.pojo.SysRegionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : xuqiang
 * @Description : 全国省市区信息表service
 * @Date: 2017/7/13
 */
@Service
public class RegionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionService.class);

    @Autowired
    private SysRegionMapper regionMapper;

    @Autowired
    private SmsService smsService;
    
    /**
     * @return 一级省市列表
     */
    public List<SysRegion> getAllProvince() {
    	LOGGER.info("RegionService.getAllProvince");
    	SysRegionExample region = new SysRegionExample();
    	region.createCriteria().andParentcodeEqualTo(GlobalConstant.chinaCode);
    	return regionMapper.selectByExample(region);
    }
    
    /**
     * 根据区域ID list 查询符合条件的区域信息
     * @param codes codeID list
     * @return 
     */
    public List<SysRegion> queryByAreaCodes(List<Integer> codes) {
    	LOGGER.info("RegionService.getRegionNameByAreaCodes codes: {}",codes);
    	SysRegionExample region = new SysRegionExample();
    	region.createCriteria().andCodeIn(codes);
    	return regionMapper.selectByExample(region);
    }
    
    /**
     * 根据区域ParentCode 查询符合条件的区域信息
     * @param code ParentCode
     * @return 
     */
    public List<SysRegion> queryByParentCode(Integer code) {
    	LOGGER.info("RegionService.queryByParentCode code: {}",code);
    	SysRegionExample region = new SysRegionExample();
    	region.createCriteria().andParentcodeEqualTo(code);
    	return regionMapper.selectByExample(region);
    }
}
