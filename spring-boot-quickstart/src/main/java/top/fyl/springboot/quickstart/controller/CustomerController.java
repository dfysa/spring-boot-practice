package top.fyl.springboot.quickstart.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.quickstart.enums.RequestType;
import top.fyl.springboot.quickstart.service.CustomerService;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:19
 * @description
 */

@RestController
@RequestMapping("/requests")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/{type}")
    public String handleRequest(@PathVariable RequestType type) {
        return customerService.handleRequest(type);
    }
}
