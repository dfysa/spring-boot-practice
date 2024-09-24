package top.fyl.springboot.configure.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.springboot.configure.entity.Special;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 2024/9/23 下午7:09
 * @description
 */
@SpringBootTest
class SpecialMapperTest {
    @Resource
    private SpecialMapper specialMapper;
    @Test
    void findAll() {
        List<Special> specials = specialMapper.findAll();
        specials.forEach(System.out::println);
    }
}
