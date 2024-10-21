package top.fyl.springboot.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dfysa
 * @data 2024/10/21 下午1:47
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = {"top.fyl.springboot.redis.mapper"})
public class RedisApplication {
    public static void main(String[] args) {   SpringApplication.run(RedisApplication.class,args);
    }
}