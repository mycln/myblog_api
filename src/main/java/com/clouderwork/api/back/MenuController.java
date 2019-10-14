package com.clouderwork.api.back;

import com.clouderwork.annotation.ControllerLogs;
import com.clouderwork.annotation.DuplicateSubmitToken;
import com.clouderwork.common.CommResult;
import com.clouderwork.params.SysMenuParams;
import com.clouderwork.service.MenusService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/11/22
 */
@CrossOrigin(origins = "*")
@RestController
@Api(description = "S04 系统菜单管理接口")
@RequestMapping(value = "/back/menus")
public class MenuController {
    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenusService menusService;

    @DuplicateSubmitToken(type = DuplicateSubmitToken.REQUEST)
    @ApiOperation(value = "S0401 菜单增加 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ControllerLogs(description = "菜单添加")
    public CommResult add(@Valid @RequestBody SysMenuParams msg) throws Exception{
        menusService.add(msg);
        return CommResult.ok();
    }

    @ApiOperation(value = "S0402 菜单删除 状态:已完成", response = CommResult.class)
    @PostMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ControllerLogs(description = "菜单删除")
    public CommResult del(
            @ApiParam(value = "菜单id",required = true) @RequestParam(value = "id") Integer id
    ) {
        menusService.del(id);
        return CommResult.ok();
    }
}
