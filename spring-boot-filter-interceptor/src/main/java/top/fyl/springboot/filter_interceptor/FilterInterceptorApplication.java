package top.fyl.springboot.filter_interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author dfysa
 * @data 2024/11/4 下午1:36
 * @description
 */

@SpringBootApplication
//@ServletComponentScan
public class FilterInterceptorApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterInterceptorApplication.class, args);
    }
}

