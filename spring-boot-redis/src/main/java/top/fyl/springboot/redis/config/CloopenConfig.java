package top.fyl.springboot.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dfysa
 * @data 2024/10/21 下午2:57
 * @description cloopenconfig,统一读取短信发送的配置信息
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "fyl.sms.ccp")
public class CloopenConfig {
    private String serverIp;
    private String port;
    private String accountSId;
    private String accountToken;
    private String appId;
    private String templateId;
}
