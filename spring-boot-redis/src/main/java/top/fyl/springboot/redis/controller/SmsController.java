package top.fyl.springboot.redis.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.redis.result.Result;
import top.fyl.springboot.redis.service.SmsService;

/**
 * @author dfysa
 * @data 2024/10/21 下午3:08
 * @description
 */
@RestController

@AllArgsConstructor
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public Result<Object> sendSms(@RequestParam("phone") String phone){
        smsService.sendSms(phone);
        return Result.ok();
    }
}
