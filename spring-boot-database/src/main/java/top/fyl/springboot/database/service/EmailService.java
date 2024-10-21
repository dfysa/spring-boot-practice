package top.fyl.springboot.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>(); // 存储邮箱和验证码
    private final ThreadPoolTaskScheduler scheduler; // 管理定时任务

    @Value("${spring.mail.username}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;

        // 初始化线程池管理的定时任务
        this.scheduler = new ThreadPoolTaskScheduler();
        this.scheduler.setPoolSize(1);
        this.scheduler.initialize();
    }

    // 生成6位随机验证码
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    // 发送验证码邮件并缓存验证码
    public void sendVerificationEmail(String to) {
        String code = generateVerificationCode(); // 生成验证码

        // 存储验证码并设置10分钟后过期
        verificationCodes.put(to, code);
        scheduleCodeExpiry(to); // 定时任务删除验证码

        // 构建邮件内容
        String subject = "验证码";
        String body = "您的注册验证码是：" + code + "。请在10分钟内使用。";

        // 发送邮件
        sendSimpleEmail(to, subject, body);
    }

    // 定时任务：10分钟后删除验证码
    private void scheduleCodeExpiry(String to) {
        scheduler.schedule(() -> {
            verificationCodes.remove(to);
            log.info("验证码已过期，已从缓存中移除: {}", to);
        }, Instant.ofEpochSecond(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)));
    }

    // 校验验证码
    public boolean verifyCode(String to, String code) {
        String storedCode = verificationCodes.get(to);
        return code.equals(storedCode);
    }

    // 通用邮件发送方法
    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        log.info("邮件已发送到: {}", to);
    }
}
