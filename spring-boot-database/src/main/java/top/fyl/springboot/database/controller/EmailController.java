package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.entity.Mail;
import top.fyl.springboot.database.service.EmailService;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @author dfysa
 * @data 2024/9/23 下午8:30
 * @description
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {
    @Resource
    private final EmailService emailService;

//    // 发送验证码
//    @PostMapping("/sendCode")
//    public ResponseEntity<String> sendCode(@RequestParam String email) {
//        emailService.sendVerificationEmail(email);
//        return ResponseEntity.ok("验证码已发送");
//    }

    // 发送验证码
    @PostMapping("/sendCode")
    public ResponseEntity<String> sendCode(@RequestBody Mail mail) {
        emailService.sendVerificationEmail(mail.getTo()); // 发送验证码
        return ResponseEntity.ok("验证码已发送");
    }

    // 校验验证码
    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyCode(
            @RequestParam String email,
            @RequestParam String code) {
        boolean isValid = emailService.verifyCode(email, code);
        if (isValid) {
            return ResponseEntity.ok("验证码正确");
        } else {
            return ResponseEntity.status(400).body("验证码错误或已过期");
        }
    }


    @PostMapping("/mail")
    public void sendEmail(@RequestBody Mail mail) {
        emailService.sendSimpleEmail(mail.getTo(), mail.getSubject(), mail.getBody());
    }
}
