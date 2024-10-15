package top.fyl.springboot.database.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dfysa
 * @data 2024/10/14 下午9:23
 * @description
 */
@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>(); // 存储邮箱和验证码
    private final ThreadPoolTaskScheduler scheduler; // 线程池管理的定时任务

    @Value("${spring.mail.username}")
    private String from;

    // 构造函数注入
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;

        // 初始化线程池定时任务
        this.scheduler = new ThreadPoolTaskScheduler();
        this.scheduler.setPoolSize(1);
        this.scheduler.initialize();
    }

    // 发送验证码并在10分钟后过期
    public void sendVerificationEmail(String to) {
        String code = generateVerificationCode();

        // 存储验证码
        verificationCodes.put(to, code);

        // 定时任务：10分钟后移除验证码
        scheduleCodeExpiry(to);

        // 构建邮件信息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("注册验证码");
        message.setText("您的注册验证码是：" + code + "。请在10分钟内使用。");

        // 发送邮件
        mailSender.send(message);
        log.info("验证码已发送到: {}", to);
    }

    // 生成6位随机验证码
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
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
}