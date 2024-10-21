package top.fyl.springboot.redis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dfysa
 * @data 2024/10/21 下午4:05
 * @description
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("fyl 的 API")
                        .contact(new Contact().name("mqxu").email("846241197.@qq.com"))
                .version("1.0")
                .description("fyl 的 API 接⼝⽂档")
                .license(new License().name("Apache 2.0").url("http://doc.xiaominfo.com")));
    }
}
