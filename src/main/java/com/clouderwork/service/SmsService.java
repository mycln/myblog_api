package com.clouderwork.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.clouderwork.common.IDGenerator;
import com.clouderwork.contant.GlobalConstant;
import com.clouderwork.dao.SmsRecordMapper;
import com.clouderwork.enums.YESNOEnum;
import com.clouderwork.pojo.SmsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

/**
 * @Author xuqiang
 * @Contact
 * @Description 短信服务
 * @Date Created in 2018/9/13
 */
@Service
public class SmsService {
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsRecordMapper smsRecordMapper;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    public String sendMessage(String phone) throws Exception{
        SendSmsResponse response = this.sendSms(phone);
        return response.getCode();
    }

    /**
     * 校验用户输入的验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    public Boolean isCodeTrue(String phone,String code){
        if(null==redisService.getStr(phone)){
            throw new IllegalArgumentException("请重新获取验证码!");
        }
        if(code.equals(redisService.getStr(phone))){
            return true;
        }
        return false;
    }

    @Transactional
    private SendSmsResponse sendSms(String phone) throws Exception {
        Random random = new Random();
        String code = random.nextInt(999999) + "";//验证码
        int randLength = code.length();
        if(randLength<6){
            for(int i=1; i<=6-randLength; i++)
                code = "1" + code  ;
        }

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", GlobalConstant.accessKeyId, GlobalConstant.accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName(GlobalConstant.signName);
        request.setTemplateCode(GlobalConstant.templateCode);
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        SmsRecord record = new SmsRecord();
        record.setId(IDGenerator.getRandomID());
        record.setContent(code);
        record.setCodeDesc(sendSmsResponse.getMessage());
        record.setBizid(sendSmsResponse.getBizId());
        record.setReceivePhone(phone);
        if("ok".equalsIgnoreCase(sendSmsResponse.getCode())){
            record.setSuccess(YESNOEnum.YES.getValue());
            redisService.setExpireStr(phone,code,300l);
        }else{
            record.setSuccess(YESNOEnum.NO.getValue());
        }
        record.setSendTime(new Date());
        smsRecordMapper.insertSelective(record);

        return sendSmsResponse;
    }

//    public QuerySendDetailsResponse querySendDetails(String bizId) throws Exception {
//
//        //可自助调整超时时间
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        //初始化acsClient,暂不支持region化
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", GlobalConstant.accessKeyId, GlobalConstant.accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//
//        //组装请求对象
//        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
//        //必填-号码
//        request.setPhoneNumber("15000000000");
//        //可选-流水号
//        request.setBizId(bizId);
//        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
//        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
//        request.setSendDate(ft.format(new Date()));
//        //必填-页大小
//        request.setPageSize(10L);
//        //必填-当前页码从1开始计数
//        request.setCurrentPage(1L);
//
//        //hint 此处可能会抛出异常，注意catch
//        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
//
//        return querySendDetailsResponse;
//    }

    public static void main(String[] args) throws Exception{

//        //发短信
//        SendSmsResponse response = sendSms("13260015267");
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());
//
//        Thread.sleep(3000L);
//
//        //查明细
//        if(response.getCode() != null && response.getCode().equals("OK")) {
////            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
////            System.out.println("短信明细查询接口返回数据----------------");
////            System.out.println("Code=" + querySendDetailsResponse.getCode());
////            System.out.println("Message=" + querySendDetailsResponse.getMessage());
////            int i = 0;
////            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
////            {
////                System.out.println("SmsSendDetailDTO["+i+"]:");
////                System.out.println("Content=" + smsSendDetailDTO.getContent());
////                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
////                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
////                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
////                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
////                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
////                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
////                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
////            }
////            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
////            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//            System.out.println("发送成功");
//        }

    }
}
