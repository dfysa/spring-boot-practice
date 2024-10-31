package top.fyl.springboot.task.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author dfysa
 * @data 2024/10/28 下午5:27
 * @description
 */


@Service
@Slf4j
public class EmailService {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private final WeatherService weatherService;

    public EmailService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void sendWeatherEmail(String to) {
        try {
            String weatherInfo = weatherService.getWeather(); // 不再传入城市参数
            String subject = "天气预报";
            SimpleMailMessage message = new SimpleMailMessage();
            // 发件人邮箱
            message.setFrom(from);
            // 收件人邮箱
            message.setTo(to);
            // 邮件主题
            message.setSubject(subject);
            // 邮件内容
            message.setText(weatherInfo);
            // 发送邮件
            mailSender.send(message);
            log.info("邮件已发送至 {}，主题：{}", to, subject);
        } catch (Exception e) {
            log.error("发送邮件失败：{}", e.getMessage());
        }
    }
}