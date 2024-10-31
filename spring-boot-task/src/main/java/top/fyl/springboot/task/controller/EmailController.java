package top.fyl.springboot.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.task.Service.EmailService;

/**
 * @author dfysa
 * @data 2024/10/28 下午5:29
 * @description
 */
@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/sendWeatherEmail")
    public void sendWeatherEmail(@RequestParam String to) {
        emailService.sendWeatherEmail(to);
    }
}