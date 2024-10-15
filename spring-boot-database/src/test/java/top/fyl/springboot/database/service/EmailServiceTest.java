package top.fyl.springboot.database.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * @author dfysa
 * @data 2024/10/14 下午9:50
 * @description
 */

class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testSendVerificationEmail() {
//        // 定义测试输入
//        String to = "846241197@qq.com";
//        String code = "123456";
//
//        // 执行要测试的方法
//        emailService.sendVerificationEmail(to, code);
//
//        // 捕获传递给 mailSender 的 SimpleMailMessage
//        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
//        verify(mailSender).send(captor.capture());
//
//        // 验证捕获的邮件内容是否符合预期
//        SimpleMailMessage sentMessage = captor.getValue();
//        assertEquals(to, sentMessage.getTo()[0]);
//        assertEquals("注册验证码", sentMessage.getSubject());
//        assertEquals("您的注册验证码是：123456。请在10分钟内使用。", sentMessage.getText());
//    }
}