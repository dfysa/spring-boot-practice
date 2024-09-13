package top.fyl.springboot.thymeleaf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author dfysa
 * @data 12/9/2024 下午3:11
 * @description
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String name) {
        // 正则表达式，允许的字符为字母和空格
        String regex = "^[a-zA-Z\\s]+$";

        if (name == null || name.trim().isEmpty()) {
            logger.warn("Received empty or null name");
            return "你的名字有误，请提供有效的名字！";
        }

        // 检查是否包含特殊字符
        if (!name.matches(regex)) {
            logger.warn("Received invalid name with special characters: {}", name);
            return "你的名字有误，请不要包含特殊字符！";
        }

        logger.info("Greeting request for name: {}", name);
        return "Hello, " + name + "!";
    }

    @GetMapping("/factorial/{number}")
    public String calculateFactorial(@PathVariable int number) {
        if (number > 20) {
            logger.warn("Factorial for {} is too large to calculate", number);
            return "Factorial is too large to calculate for number: " + number;
        }
        int result = factorial(number);
        logger.info("Calculated factorial for number: {}, result: {}", number, result);
        return "Factorial of " + number + " is: " + result;
    }

    @GetMapping("/divide/{a}/{b}")
    public String divideNumbers(@PathVariable int a, @PathVariable int b) {
        try {
            int result = divide(a, b);
            logger.info("Division of {} by {} is: {}", a, b, result);
            return "Result of " + a + " divided by " + b + " is: " + result;
        } catch (ArithmeticException e) {
            logger.error("Error during division: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    // 计算阶乘的递归方法
    private int factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (number == 0 || number == 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }

    // 执行除法
    private int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}
