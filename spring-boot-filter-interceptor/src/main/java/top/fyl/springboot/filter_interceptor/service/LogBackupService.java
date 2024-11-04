package top.fyl.springboot.filter_interceptor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author dfysa
 * @data 2024/11/4 下午6:17
 * @description
 */
@Service
public class LogBackupService {

    @Value("${logging.file.name}")
    private String logFileName;

    public void backupLogFile() {
        File logFile = new File(logFileName);
        if (logFile.exists()) {
            File backupFile = new File(logFile.getParent(), "backup-" + logFile.getName());
            try {
                Files.copy(logFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("日志文件已备份到: " + backupFile.getPath());
            } catch (IOException e) {
                System.err.println("备份日志文件失败: " + e.getMessage());
            }
        } else {
            System.out.println("日志文件不存在: " + logFileName);
        }
    }
}