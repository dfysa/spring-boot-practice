package top.fyl.springboot.configure.service;

import cn.hutool.core.io.FileUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author dfysa
 * @data 2024/9/23 下午8:23
 * @description
 */
@Service
@Slf4j
public class ConfigBackupService {
    @Value("${custom.backup.location}")
    private String configFilePath;
    @PostConstruct
    public void backupConfigFile() {
        File configFile = new File(configFilePath);
        if (configFile.exists()) {
            File backupFile = new File("backup/" + configFile.getName());
            FileUtil.copy(configFile, backupFile, true);
            log.info("配置⽂件已备份到：{}", backupFile.getPath());
        }
    }
}
