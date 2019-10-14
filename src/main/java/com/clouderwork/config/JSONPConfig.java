package com.clouderwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @Author heyanfeng
 * @Contact
 * @Description  前端接口调用，URL 拼上 ?callback=callback  返回的接口即为jsonp格式
 * @Date Created in 2018/8/15
 */
@Configuration
@ControllerAdvice(basePackages = "com.clouderwork.api")
public class JSONPConfig extends AbstractJsonpResponseBodyAdvice {
    public JSONPConfig(){
        super("callback","jsonp");
    }

    /**
     * 前端jsonp方式请求demo
     *
     * function testJsonp() {
     *         $.ajax({
     *             type:'get',
     *             url:'http://localhost:8003/jsonp/testJsonp',
     *             dataType:'jsonp',
     *             jsonp:"callback",
     *             success:function (data) {
     *                 alert(data.userName+"  "+data.passWord);
     *             },
     *             error:function (err) {
     *                 alert('出现错误了!!!');
     *             }
     *         });
     *     }
     *
     */
}
