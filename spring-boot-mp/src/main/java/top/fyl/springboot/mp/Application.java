package top.fyl.springboot.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dfysa
 * @data 2024/10/14 下午1:42
 * @description
 */

@SpringBootApplication
@MapperScan(basePackages = {"top.fyl.springboot.mp.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}