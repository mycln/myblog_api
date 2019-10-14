package com.clouderwork.service;

import com.clouderwork.annotation.ServiceLogs;
import com.clouderwork.common.TreeParser;
import com.clouderwork.contant.GlobalConstant;
import com.clouderwork.dao.SysMenuMapper;
import com.clouderwork.dao.SysRoleMapper;
import com.clouderwork.dao.SysRoleMenuMapper;
import com.clouderwork.dao.SysUserRoleMapper;
import com.clouderwork.dao.ext.SysMapperExt;
import com.clouderwork.enums.StatusEnum;
import com.clouderwork.enums.YESNOEnum;
import com.clouderwork.pojo.*;
import com.clouderwork.pojo.vo.SysMenuVo;
import com.clouderwork.pojo.vo.SysRoleVo;
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
public class SysService {

	private static final Logger logger = LoggerFactory.getLogger(SysService.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMapperExt sysMapperExt;
    /**
     * 角色添加
     * @param name
     * @param desc
     */
	@Transactional
	public void add(String name , String desc){
        if(isRoleNameExist(name)){
            throw new IllegalArgumentException("当前角色已存在");
        }
        SysRole role = new SysRole();
        role.setId(0);
        role.setRoleName("");
        role.setRoleDesc("");
        role.setStatus(0);
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());


        role.setRoleName(name);
        role.setRoleDesc(desc);
        role.setStatus(StatusEnum.INUSE.getValue());
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        sysRoleMapper.insertSelective(role);
	}

    /**
     * 角色更新
     * @param id
     * @param name
     * @param desc
     */
    @Transactional
    public void update(Integer id, String name , String desc,Integer status) {
        SysRole role = sysRoleMapper.selectByPrimaryKey(id);
        if (!StringUtils.isEmpty(name) && !role.getRoleName().equals(name) && isRoleNameExist(name)) {
            throw new IllegalArgumentException("当前角色已存在");
        }
        role.setRoleName(name);
        role.setRoleDesc(desc);
        role.setStatus(status);
        role.setUpdateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 判断角色名称是否已存在
     * @param name
     * @return
     */
    private Boolean isRoleNameExist(String name){
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andRoleNameEqualTo(name).andStatusNotEqualTo(StatusEnum.DEL.getValue());
        if (sysRoleMapper.countByExample(example) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 角色删除
     * @param id
     */
    @Transactional
    public void del(Integer id){
        SysRole role = sysRoleMapper.selectByPrimaryKey(id);
        if(role!=null){
            role.setStatus(StatusEnum.DEL.getValue());
            role.setUpdateTime(new Date());
            sysRoleMapper.updateByPrimaryKeySelective(role);
        }else{
            throw new IllegalArgumentException("当前角色不存在");
        }
    }

    /**
     * 角色查询
     * @param name
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<SysRoleVo> list(String name,Integer status,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria =  example.createCriteria();
        if(status!=null){
            criteria.andStatusEqualTo(status);
        }else{
            criteria.andStatusNotEqualTo(StatusEnum.DEL.getValue());
        }
        if(!StringUtils.isEmpty(name)){
           criteria.andRoleNameLike("%"+name+"%");
        }
        example.setOrderByClause("add_time desc");
        List<SysRole> list = sysRoleMapper.selectByExample(example);
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        List<SysRoleVo> retList = Lists.newArrayList();
        for (SysRole type : pageInfo.getList()) {
            SysRoleVo vo = new SysRoleVo();
            BeanUtils.copyProperties(type,vo);
            retList.add(vo);
        }

        PageInfo<SysRoleVo> ret = new PageInfo<SysRoleVo>(retList);
        BeanUtils.copyProperties(pageInfo,ret);
        ret.setList(retList);
        return ret;
    }

    /**
     * 用户添加角色
     * @param userid
     * @param roleids
     */
    @Transactional
    @ServiceLogs(description = "用户添加角色")
    public void userAddRole(Long userid, Integer[] roleids){
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUseridEqualTo(userid);
        sysUserRoleMapper.deleteByExample(example);

        for(Integer role : roleids){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleid(role);
            sysUserRole.setUserid(userid);
            sysUserRole.setAddTime(new Date());
            sysUserRoleMapper.insertSelective(sysUserRole);
        }
    }

    /**
     * 角色添加权限
     * @param roleid
     * @param menuids
     */
    @Transactional
    public void roleAddMenu(Integer roleid, Integer[] menuids){
        SysRoleMenuExample example = new SysRoleMenuExample();
        example.createCriteria().andRoleidEqualTo(roleid);
        sysRoleMenuMapper.deleteByExample(example);

        for(Integer menu : menuids){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleid(roleid);
            sysRoleMenu.setMenuid(menu);
            sysRoleMenu.setAddTime(new Date());
            sysRoleMenuMapper.insertSelective(sysRoleMenu);
        }
    }

    /**
     * 获取当前用户菜单权限列表
     */
    public List<SysMenuVo> userMenuList(Long userid){
        List<SysMenuVo> list = sysMapperExt.getUserMenuList(userid);
        List<SysMenuVo> ret = TreeParser.getTreeList(0,list);
        for(SysMenuVo vo : ret){
            setBtnPerm(vo);
        }
        return ret;
    }

    /**
     * 只set 叶子节点的按钮权限,非叶子节点的菜单不设
     * @param vo
     * @return
     */
    private void setBtnPerm(SysMenuVo vo){
        if(YESNOEnum.YES.getValue()==vo.getIsmenu() && !CollectionUtils.isEmpty(vo.getNodes())){
            String perms = "";
            for(SysMenuVo cvo : vo.getNodes()){
                if(cvo.getIsmenu()==YESNOEnum.NO.getValue()){//是按钮
                    perms += ","+ cvo.getCode();
                }else{
                    setBtnPerm(cvo);
                }
            }
            if(!StringUtils.isEmpty(perms)){
                vo.setBtnPermissions(perms.substring(1));
            }
        }
        if(YESNOEnum.YES.getValue()==vo.getIsmenu() && CollectionUtils.isEmpty(vo.getNodes())){
            vo.setBtnPermissions(GlobalConstant.permStr);
        }
    }

    /**
     * 获取所有菜单权限树
     */
    public List<SysMenuVo> menuListAll(Integer roleId){
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andStatusEqualTo(StatusEnum.INUSE.getValue());
        example.setOrderByClause("levels asc,sort asc");
        List<SysMenu> list = sysMenuMapper.selectByExample(example);
        List<SysMenuVo> parm = Lists.newArrayList();
        List<Integer> mids = Lists.newArrayList();
        if(roleId!=null){
            SysRoleMenuExample roleMenuExample = new SysRoleMenuExample();
            roleMenuExample.createCriteria().andRoleidEqualTo(roleId);
            List<SysRoleMenu> menus = sysRoleMenuMapper.selectByExample(roleMenuExample);
            if(!CollectionUtils.isEmpty(menus)){
                for(SysRoleMenu roleMenu:menus){
                    mids.add(roleMenu.getMenuid());
                }
            }
        }
        for(SysMenu menu : list){
            SysMenuVo vo = new SysMenuVo();
            BeanUtils.copyProperties(menu,vo);
            vo.setChecked(false);
            if(mids!=null && mids.contains(menu.getId())){
                vo.setChecked(true);
            }
            parm.add(vo);
        }

        List<SysMenuVo> ret = TreeParser.getTreeList(0,parm);
        return ret;
    }

}
