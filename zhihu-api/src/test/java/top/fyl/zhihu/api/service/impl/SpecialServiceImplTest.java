package top.fyl.zhihu.api.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.zhihu.api.entity.Special;
import top.fyl.zhihu.api.service.SpecialService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 14/9/2024 下午2:24
 * @description
 */
@SpringBootTest
class SpecialServiceImplTest {

    @Resource
    private SpecialService specialService;


    @Test
    void getAll() {
        List<Special> all =specialService.getAll();
        all.forEach(System.out::println);
    }
}