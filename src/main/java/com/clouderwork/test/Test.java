package com.clouderwork.test;

import com.clouderwork.Starter;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xuqiang
 * @Contact QQ、手机号或者云沃客账号
 * @Description
 * @Date Created in 2018/7/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
public class Test {


    @org.junit.Test
    public void test(){
        Integer stime = Integer.valueOf(new SimpleDateFormat("yyyyMMddHH").format(new Date()));
        System.out.println(stime);
    }
}
