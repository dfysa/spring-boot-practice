package top.fyl.springboot.configure.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 23/9/2024 下午6:24
 * @description
 */
@Slf4j
@SpringBootTest
class TestBeanServiceTest {
    //注⼊Spring上下⽂环境
    @Resource
    private ConfigurableApplicationContext ioc;
    @Test
    public void testLoadService() {
        //测试Spring上下⽂环境中是否有testBeanService这样⼀个bean，有的话表示xml配置⽂件⽣效
        boolean flag = ioc.containsBean("testBeanService");
        assertTrue(flag);
        TestBeanService testBeanService = (TestBeanService) ioc.getBean("testBeanService");
                log.info(String.valueOf(testBeanService));
        assertEquals("SpringBoot", testBeanService.getName());
    }
}