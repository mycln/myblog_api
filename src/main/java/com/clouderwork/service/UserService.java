package com.clouderwork.service;

import com.clouderwork.annotation.ServiceLogs;
import com.clouderwork.common.IDGenerator;
import com.clouderwork.common.MD5;
import com.clouderwork.contant.GlobalConstant;
import com.clouderwork.dao.SysRoleMapper;
import com.clouderwork.dao.SysUserMapper;
import com.clouderwork.dao.SysUserRoleMapper;
import com.clouderwork.enums.StatusEnum;
import com.clouderwork.pojo.*;
import com.clouderwork.pojo.vo.LoginUser;
import com.clouderwork.pojo.vo.SysUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author xuqiang
 * @Contact QQ、手机号或者云沃客账号
 * @Description
 * @Date Created in 2017/11/18
 */

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 用户添加
     * @param name
     * @param pass
     */
	@Transactional
	public void add(String name, String pass,String avatar,String phone){
        if(isUserNameExist(name)){
            throw new IllegalArgumentException("当前用户已存在");
        }
        SysUser user = new SysUser();
        user.setId(IDGenerator.getRandomID());
        user.setUsername(name);
        user.setAvatar(commonService.getSaveImgPath(avatar));
        user.setPhone(phone);
        user.setSalt(CommonService.getRandomStr(10));
        MD5 m = new MD5();
        //加密逻辑
        user.setPassword(m.getMD5ofStr(m.getMD5ofStr(pass)+ GlobalConstant.passStr + user.getSalt()));
        user.setStatus(StatusEnum.INUSE.getValue());
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        sysUserMapper.insertSelective(user);
	}

    /**
     * 用户更新
     * @param id
     * @param name
     */
    @Transactional
    public void update(Long id, String name, String avatar, String phone, Integer status) {
        SysUser user = sysUserMapper.selectByPrimaryKey(id);
        if (!StringUtils.isEmpty(name) && !user.getUsername().equals(name) && isUserNameExist(name)) {
            throw new IllegalArgumentException("当前用户名称已存在");
        }
        user.setUsername(name);
        user.setAvatar(commonService.getSaveImgPath(avatar));
        user.setPhone(phone);
        user.setStatus(status);
        user.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 判断用户名称是否已存在
     * @param name
     * @return
     */
    private Boolean isUserNameExist(String name){
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(name).andStatusNotEqualTo(StatusEnum.DEL.getValue());
        if (sysUserMapper.countByExample(example) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 用户删除
     * @param id
     */
    @Transactional
    public void del(Long id){
        SysUser user = sysUserMapper.selectByPrimaryKey(id);
        if(user!=null){
            user.setStatus(StatusEnum.DEL.getValue());
            user.setUpdateTime(new Date());
            sysUserMapper.updateByPrimaryKeySelective(user);
        }else{
            throw new IllegalArgumentException("当前用户不存在");
        }
    }

    /**
     * 用户查询
     * @param name
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<SysUserVo> list(String name, Integer status, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria =  example.createCriteria();
        if(status!=null){
            criteria.andStatusEqualTo(status);
        }else{
            criteria.andStatusNotEqualTo(StatusEnum.DEL.getValue());
        }
        if(!StringUtils.isEmpty(name)){
           criteria.andUsernameLike("%"+name+"%");
        }
        example.setOrderByClause("add_time desc");
        List<SysUser> list = sysUserMapper.selectByExample(example);
        for(SysUser su : list) {
            su.setAvatar(commonService.getFullImgPath(su.getAvatar()));
        }
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        List<SysUserVo> retList = Lists.newArrayList();
        for (SysUser type : pageInfo.getList()) {
            SysUserVo vo = new SysUserVo();
            BeanUtils.copyProperties(type,vo);
            //查询当前用户的角色
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUseridEqualTo(type.getId());
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
            if(!CollectionUtils.isEmpty(sysUserRoles)){
                List<Integer> ids = Lists.newArrayList();
                for(SysUserRole sysUserRole : sysUserRoles){
                    ids.add(sysUserRole.getRoleid());
                }
                SysRoleExample sysRoleExample = new SysRoleExample();
                sysRoleExample.createCriteria().andStatusNotEqualTo(StatusEnum.DEL.getValue()).andIdIn(ids);
                vo.setSysRole(sysRoleMapper.selectByExample(sysRoleExample));
            }
            retList.add(vo);
        }
        PageInfo<SysUserVo> ret = new PageInfo<SysUserVo>(retList);
        BeanUtils.copyProperties(pageInfo,ret);
        ret.setList(retList);
        return ret;
    }

    @Transactional
    public void changePass(String oldPass,String newPass){
        LoginUser loginUser = commonService.getCurreUser();
        if(loginUser!=null){
            SysUser user = sysUserMapper.selectByPrimaryKey(loginUser.getId());
            if(user!=null){
                if(CommonService.isPassTrue(user.getPassword(),oldPass,user.getSalt())){
                    user.setPassword(CommonService.getPassMd5(newPass,user.getSalt()));
                    user.setUpdateTime(new Date());
                    sysUserMapper.updateByPrimaryKeySelective(user);
                }else{
                    throw new IllegalArgumentException("旧密码不正确");
                }
            }
        }else{
            throw new IllegalArgumentException("请重新登录!");
        }
    }

}
