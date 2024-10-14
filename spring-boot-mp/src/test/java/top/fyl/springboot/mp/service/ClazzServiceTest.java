package top.fyl.springboot.mp.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.springboot.mp.entity.Clazz;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 2024/10/14 下午3:41
 * @description
 */
@SpringBootTest
@Slf4j
class ClazzServiceTest {
    @Resource
    private ClazzService clazzService;
    @Test
    void getClazzWithTeacher() {
        Clazz clazzWithTeacher = clazzService.getClazzWithDetails(1L);
        log.info(clazzWithTeacher.toString());
    }
}
