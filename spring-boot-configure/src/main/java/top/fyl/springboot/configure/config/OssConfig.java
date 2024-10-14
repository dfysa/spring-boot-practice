package top.fyl.springboot.configure.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dfysa
 * @data 2024/9/23 下午7:27
 * @description
 */
@Data
@Configuration
public class OssConfig {
    @Value("8")
    private String endpoint;
    @Value("8")
    private String accessKeyId;
    @Value("8")
    private String accessKeySecret;
    @Value("8")
    private String bucketName;
    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
