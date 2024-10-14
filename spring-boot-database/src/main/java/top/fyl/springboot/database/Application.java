package top.fyl.springboot.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dfysa
 * @data 23/9/2024 下午1:40
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = {"top.fyl.springboot.database.mapper"})
public class Application {
    public static void main(String[] args) {   SpringApplication.run(Application.class,args);
    }
}

