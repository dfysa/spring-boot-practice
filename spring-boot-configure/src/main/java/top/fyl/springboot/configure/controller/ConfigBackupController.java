package top.fyl.springboot.configure.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.service.ConfigBackupService;

/**
 * @author dfysa
 * @data 2024/9/23 下午8:23
 * @description
 */
@RestController
@AllArgsConstructor
public class ConfigBackupController {
    private final ConfigBackupService configBackupService;
    @GetMapping("/backup")
    public void backupConfig() {
        configBackupService.backupConfigFile();
    }
}