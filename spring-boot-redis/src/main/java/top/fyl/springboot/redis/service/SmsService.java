package top.fyl.springboot.redis.service;

/**
 * @author dfysa
 * @data 2024/10/21 下午2:59
 * @description
 */
public interface SmsService {

    /*
    * 发送短信
    * */
    void sendSms(String phone);

}
