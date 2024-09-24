package top.fyl.springboot.configure.entity;

import com.sun.tools.javac.Main;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FamilyTest {
    @Resource
    private Family family;

    @Test
    void FamilyTest() {
        System.out.println(family);
    }

}