package top.fyl.springboot.task.config;

/**
 * @author dfysa
 * @data 2024/10/28 下午5:29
 * @description
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
