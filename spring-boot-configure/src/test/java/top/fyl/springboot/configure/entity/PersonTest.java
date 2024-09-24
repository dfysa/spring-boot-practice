package top.fyl.springboot.configure.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 23/9/2024 下午3:43
 * @description
 */
@SpringBootTest
class PersonTest {
    @Resource
    private Person person;
    @Test
    void Persontest() {
        System.out.println(person);
    }
}