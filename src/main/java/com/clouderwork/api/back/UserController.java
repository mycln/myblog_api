package com.clouderwork.api.back;

import com.clouderwork.annotation.ControllerLogs;
import com.clouderwork.annotation.DuplicateSubmitToken;
import com.clouderwork.annotation.ServiceLogs;
import com.clouderwork.common.CommResult;
import com.clouderwork.pojo.vo.SysUserVo;
import com.clouderwork.service.UserService;
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
@Api(description = "S02 用户管理接口")
@RequestMapping(value = "/back/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0201 用户增加 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult add(
            @ApiParam(value = "用户名称", required = true) @RequestParam(value = "name") String name,
            @ApiParam(value = "用户密码", required = true) @RequestParam(value = "pass") String pass,
            @ApiParam(value = "用户头像相对地址") @RequestParam(value = "avatar",required = false) String avatar,
            @ApiParam(value = "用户手机号") @RequestParam(value = "phone",required = false) String phone
    ) {
        userService.add(name,pass,avatar,phone);
        return CommResult.ok();
    }

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0202 用户编辑 状态：未完成", response = CommResult.class)
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ControllerLogs(description = "用户更新")
    public CommResult update(
            @ApiParam(value = "用户id", required = true) @RequestParam(value = "id") Long id,
            @ApiParam(value = "用户名称") @RequestParam(value = "name",required = false) String name,
            @ApiParam(value = "用户头像相对地址") @RequestParam(value = "avatar",required = false) String avatar,
            @ApiParam(value = "用户手机号") @RequestParam(value = "phone",required = false) String phone,
            @ApiParam(value = "用户状态") @RequestParam(value = "status") Integer status) {
        userService.update(id,name,avatar,phone,status);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0203 用户删除  状态：已完成", response = CommResult.class)
    @PostMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult del(
            @ApiParam(value = "用户id", required = true) @RequestParam(value = "id") Long id) {
        userService.del(id);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0204 用户查询 状态:已完成", response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response= SysUserVo.class)
    })
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult list(
            @ApiParam(value = "用户名称") @RequestParam(value = "name",required = false) String name,
            @ApiParam(value = "1启用 2停用") @RequestParam(value = "status",required = false) Integer status,
            @ApiParam(value = "查询页数")@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页条数")@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
            ) {
        return CommResult.ok(userService.list(name,status,pageNum,pageSize));
    }

    @ApiOperation(value = "S0205 当前用户修改密码 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/changePass", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult changePass(
            @ApiParam(value = "旧密码", required = true) @RequestParam(value = "oldPass") String oldPass,
            @ApiParam(value = "新密码", required = true) @RequestParam(value = "newPass") String newPass) {
        userService.changePass(oldPass,newPass);
        return CommResult.ok();
    }
}
