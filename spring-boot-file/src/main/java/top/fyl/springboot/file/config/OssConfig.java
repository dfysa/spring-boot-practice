package top.fyl.springboot.file.config;

/**
 * @author dfysa
 * @data 2024/10/31 下午4:03
 * @description
 */
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("classpath:aliyun-oss.properties")
@ConfigurationProperties(prefix = "aliyun-oss")
@Data
public class OssConfig {
    private String endpoint;
    private String bucket;
    private String dir;
    private String host;
    private String ak;
    private String secret;
    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, ak, secret);
    }
}
