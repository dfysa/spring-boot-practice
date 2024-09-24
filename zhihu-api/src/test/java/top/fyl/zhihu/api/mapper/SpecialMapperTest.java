package top.fyl.zhihu.api.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.zhihu.api.entity.Special;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 14/9/2024 下午2:13
 * @description
 */
@SpringBootTest
class SpecialMapperTest {
    @Resource
    private SpecialMapper specialMapper;

    @Test
    void selectAll() {
        List<Special> specials = specialMapper.selectAll();
        specials.forEach(System.out::println);
    }
}