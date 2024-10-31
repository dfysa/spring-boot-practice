package top.fyl.springboot.file.utils;

import com.aliyun.oss.OSS;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.fyl.springboot.file.config.OssConfig;


import java.io.InputStream;
import java.util.UUID;

@Component
public class OssTemplate {
    @Resource
    private OSS ossClient;

    @Resource
    private OssConfig ossConfig;

    public String ossUpload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("文件名不能为空");
            }
            String fileName = ossConfig.getDir() +  UUID.randomUUID().toString() + "_" + originalFilename;
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(ossConfig.getBucket(), fileName, inputStream);
            // 构建返回的 URL，使用 host 属性
            return ossConfig.getHost() + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }
}
