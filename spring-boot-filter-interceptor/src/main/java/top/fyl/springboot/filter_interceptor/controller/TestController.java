package top.fyl.springboot.filter_interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dfysa
 * @data 2024/11/4 下午1:47
 * @description
 */
@RestController
public class TestController {
    @GetMapping("test")
    public String test(){
        return "test";
    }
}
