package top.fyl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dfysa
 * @data 2024/9/9 下午1:52
 * @description
 */
@SpringBootApplication
@RestController
@MapperScan("top.fyl.springboot.thymeleaf.mapper")
public class Main  {
    public static void main(String[] args) {

       SpringApplication.run(Main.class,args);

    }

    @GetMapping
    public  String hello(String name){
        String greeting="Hello,"+name;
        return greeting;
    }

    @GetMapping("/fib")
    public  Integer fib(Integer n){
        if (n <= 1) {
            return n;
        }
        int previous = 0, current = 1;
        for (int i = 2; i <= n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

}