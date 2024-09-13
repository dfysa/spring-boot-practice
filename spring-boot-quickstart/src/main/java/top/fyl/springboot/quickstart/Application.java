package top.fyl.springboot.quickstart;

/**
 * @author dfysa
 * @data 2024/9/2 下午3:46
 * @description
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application
                .class);
        springApplication.setLogStartupInfo(false);
        springApplication.run(args);
    }
}
