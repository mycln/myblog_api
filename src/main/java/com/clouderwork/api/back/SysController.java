package com.clouderwork.api.back;

import com.clouderwork.annotation.DuplicateSubmitToken;
import com.clouderwork.common.CommResult;
import com.clouderwork.pojo.vo.SysMenuVo;
import com.clouderwork.pojo.vo.SysRoleVo;
import com.clouderwork.service.SysService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/9/4
 */
@CrossOrigin(origins = "*")
@RestController
@Api(description = "S01 系统管理接口")
@RequestMapping(value = "/back/sys")
public class SysController {
    private static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private SysService sysService;

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0101 角色增加 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/role/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult roleadd(
            @ApiParam(value = "角色名称", required = true) @RequestParam(value = "name") String name,
            @ApiParam(value = "角色描述") @RequestParam(value = "desc",required = false) String desc) {
        sysService.add(name,desc);
        return CommResult.ok();
    }

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0102 角色编辑 状态：已完成", response = CommResult.class)
    @PostMapping(value = "/role/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult roleupdate(
            @ApiParam(value = "角色id", required = true) @RequestParam(value = "id") Integer id,
            @ApiParam(value = "角色名称") @RequestParam(value = "name",required = false) String name,
            @ApiParam(value = "1启用 2停用") @RequestParam(value = "status",required = false) Integer status,
            @ApiParam(value = "角色描述") @RequestParam(value = "desc",required = false) String desc) {
        sysService.update(id,name,desc,status);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0103 角色删除  状态：已完成", response = CommResult.class)
    @PostMapping(value = "/role/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult roledel(
            @ApiParam(value = "角色id", required = true) @RequestParam(value = "id") Integer id) {
        sysService.del(id);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0104 角色查询 状态:已完成", response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response=SysRoleVo.class)
    })
    @GetMapping(value = "/role/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult rolelist(
            @ApiParam(value = "角色名称") @RequestParam(value = "name",required = false) String name,
            @ApiParam(value = "1启用 2停用") @RequestParam(value = "status",required = false) Integer status,
            @ApiParam(value = "查询页数")@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页条数")@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
            ) {
        return CommResult.ok(sysService.list(name,status,pageNum,pageSize));
    }

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0105 角色添加权限 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/role/permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult rolePermission(
            @ApiParam(value = "角色id",required = true) @RequestParam(value = "roleId") Integer roleId,
            @ApiParam(value = "选中菜单id集合",required = true) @RequestParam(value = "menuIds") Integer[] menuIds) {
        sysService.roleAddMenu(roleId,menuIds);
        return CommResult.ok();
    }

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0106 用户添加角色 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/user/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult userAddRole(
            @ApiParam(value = "用户id",required = true) @RequestParam(value = "userId") Long userId,
            @ApiParam(value = "角色id集合",required = true) @RequestParam(value = "roleIds") Integer[] roleIds
    ) {
        sysService.userAddRole(userId,roleIds);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0107 获取指定角色的权限信息 状态:已完成", notes = "接口返回整个菜单树,前端根据每个菜单的isChecked判断是否默认选中",response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response=SysMenuVo.class)
    })
    @GetMapping(value = "/role/menu/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult roleMenuAll(
            @ApiParam(value = "角色id",required = true) @RequestParam(value = "roleId") Integer roleId) {
        return CommResult.ok(sysService.menuListAll(roleId));
    }

    @ApiOperation(value = "S0108 获取所有菜单权限列表 状态:已完成", response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response=SysMenuVo.class)
    })
    @GetMapping(value = "/menu/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult menuList() {
        return CommResult.ok(sysService.menuListAll(null));
    }

//    @ApiOperation(value = "S0108 菜单添加 状态:未完成", response = CommResult.class)
//    @PostMapping(value = "/menu/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public CommResult menuAdd() {
//        return CommResult.ok();
//    }
//
//    @ApiOperation(value = "S0109 菜单编辑 状态:未完成", response = CommResult.class)
//    @PostMapping(value = "/menu/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public CommResult menuUpdate() {
//        return CommResult.ok();
//    }
//
//    @ApiOperation(value = "S0110 菜单删除 状态:未完成", response = CommResult.class)
//    @PostMapping(value = "/menu/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public CommResult menuDel() {
//        //软删除
//        return CommResult.ok();
//    }
}
