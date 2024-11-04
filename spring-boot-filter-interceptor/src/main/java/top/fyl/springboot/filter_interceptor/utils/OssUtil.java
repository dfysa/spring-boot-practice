package top.fyl.springboot.filter_interceptor.utils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.fyl.springboot.filter_interceptor.config.OssConfig;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Component
public class OssUtil {
    @Resource
    private OSS ossClient;
    @Resource
    private OssConfig ossConfig;

    @Value("${logging.file.name}")
    private String logFileName;

    public String uploadFile(MultipartFile file, String uniqueFileName) {
        try {
            // 检查文件类型
            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                throw new RuntimeException("只支持上传图片文件");
            }

            // 创建临时文件存储上传的图片
            File tempFile = FileUtil.createTempFile("uploaded-", ".png", true);
            file.transferTo(tempFile);

            // 添加水印
            try {
                ImgUtil.pressText(
                        tempFile,
                        tempFile,
                        "你知道的，这是一个水印",
                        Color.WHITE,
                        new Font("黑体", Font.BOLD, 50),
                        0,
                        0,
                        0.7f
                );
            } catch (Exception e) {
                throw new RuntimeException("添加水印失败", e);
            }

            // 上传文件
            try (InputStream watermarkedInputStream = new FileInputStream(tempFile)) {
                String folderPath = "interceptor/";
                ossClient.putObject(ossConfig.getBucketName(), folderPath + uniqueFileName, watermarkedInputStream);
                System.out.println("文件上传成功: " + uniqueFileName);

                backupLogFile(); // 直接调用备份日志的方法
            }

            // 删除临时文件
            FileUtil.del(tempFile);

            return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint().replace("https://", "") + "/" + uniqueFileName;
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    private void backupLogFile() {
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
