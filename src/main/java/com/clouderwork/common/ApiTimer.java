package com.clouderwork.common;

import com.clouderwork.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author xuqiang
 * @Contact QQ、手机号或者云沃客账号
 * @Description
 * @Date Created in 2018/7/10
 */
@Component
public class ApiTimer {
    private static Logger logger = LoggerFactory.getLogger(ApiTimer.class);

    @Autowired
    private CommonService commonService;

    /**
     * 每日定时器 每天零点
     */
    @Scheduled(cron = "1 0 0 * * ?")
    public void executeSecQuery() throws Exception {
    }

    @Scheduled(cron = "0 10 0 * * ?")
    public void executeDel() throws Exception {
        commonService.delImgs();//清空过期验证码图片
    }
}
