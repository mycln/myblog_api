package com.clouderwork.api.back;

import com.clouderwork.common.CommResult;
import com.clouderwork.pojo.vo.SysLogsVo;
import com.clouderwork.service.SysLogsService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/11/22
 */
@CrossOrigin(origins = "*")
@RestController
@Api(description = "S03 系统日志管理接口")
@RequestMapping(value = "/back/logs")
public class LogController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private SysLogsService sysLogsService;

    @ApiOperation(value = "S0301 状态:已完成", response = CommResult.class)
    @ApiResponses({
            @ApiResponse(code=200,message="成功,返回content中vo类参数如下",response=SysLogsVo.class)
    })
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommResult rolelist(
            @ApiParam(value = "发出请求的主机的 IP 地址") @RequestParam(value = "remoteAddr",required = false) String remoteAddr,
            @ApiParam(value = "用户是否登录 0:未登录；1:已登录") @RequestParam(value = "isLogin",required = false) Integer isLogin,
            @ApiParam(value = "日志添加时间") @RequestParam(value = "addTime",required = false) String addTime,
            @ApiParam(value = "查询页数")@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(value = "每页条数")@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
            ) throws ParseException {
        return CommResult.ok(sysLogsService.list(remoteAddr,isLogin,addTime,pageNum,pageSize));
    }
}
