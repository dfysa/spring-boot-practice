package top.fyl.springboot.configure.entity;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 23/9/2024 下午6:28
 * @description
 */
@SpringBootTest
@Slf4j
public class EmployeeTest {
    @Resource
    private Employee employee;
    @Test
    public void testEmployee() throws Exception {
        log.info(String.valueOf(employee));
    }
}