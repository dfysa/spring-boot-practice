package top.fyl.springboot.quickstart.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.quickstart.service.EmployeeService;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:33
 * @description
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/address")
    public String getAddress(@RequestParam Long employeeId) {
        return employeeService.getAddressDetails(employeeId);
    }
}