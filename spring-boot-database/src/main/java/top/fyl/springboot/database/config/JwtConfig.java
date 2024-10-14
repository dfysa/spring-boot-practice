package top.fyl.springboot.database.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dfysa
 * @data 23/9/2024 下午4:19
 * @description
 */
@Data
@Configuration
@ConfigurationProperties(prefix="myapp.jwt")

public class JwtConfig {
    private String secret;
    private Long expiration;
}
