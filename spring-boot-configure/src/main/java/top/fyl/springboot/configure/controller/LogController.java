package top.fyl.springboot.configure.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.service.LogService;

/**
 * @author dfysa
 * @data 2024/9/23 下午8:17
 * @description
 */
@RestController
@AllArgsConstructor
public class LogController {
    private final LogService logService;
    @GetMapping("/log")
    public void logMsg() {
        logService.logMessage();
    }
}