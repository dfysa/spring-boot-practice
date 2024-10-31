package top.fyl.springboot.task.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import top.fyl.springboot.task.entity.EmailLog;
import top.fyl.springboot.task.mapper.EmailLogMapper;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/10/28 下午3:36
 * @description
 */
@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final EmailLogMapper emailLogMapper;

    //1,发送邮件
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("846241197@qq.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);

        //2,将发送记录存入数据库
        EmailLog emailLog = new EmailLog();
        emailLog.setRecipient(to);
        emailLog.setSubject(subject);
        emailLog.setContent(content);
        emailLog.setSentAt(LocalDateTime.now());
        emailLogMapper.insert(emailLog);

        //3，控制台输出日志
        log.info("邮件发送成功:{}",to);

    }

}