package top.fyl.springboot.configure.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.entity.Mail;
import top.fyl.springboot.configure.service.EmailService;

/**
 * @author dfysa
 * @data 2024/9/23 下午8:30
 * @description
 */

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/mail")
    public void sendEmail(@RequestBody Mail mail) {
        emailService.sendSimpleEmail(mail.getTo(), mail.getSubject(), mail.getBody());
    }
}
