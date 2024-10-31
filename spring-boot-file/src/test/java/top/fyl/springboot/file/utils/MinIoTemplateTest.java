package top.fyl.springboot.file.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;
@SpringBootTest
@Slf4j
class MinIoTemplateTest {
    @Resource
    private MinIoTemplate minIoTemplate;
    @Test
    void bucketExists() throws Exception {
        //判断是否有 avatar 这个存储桶存在
        boolean flag = minIoTemplate.bucketExists("avatar");
        log.info(String.valueOf(flag));
    }
    @Test
    void makeBucket() throws Exception {
        //创建⼀个名为 mqxu 的存储桶
        minIoTemplate.makeBucket("mqxu");
    }
    @Test
    void putObject() throws Exception {
        File file = new File("/Users/moqi/Pictures/me.png");
        // 返回上传后的访问路径
        String url = minIoTemplate.putObject("mqxu", "test/" + UUID.randomUUID() + ".png", new FileInputStream(file));
        log.info(url);
    }
    @Test
    void removeObject() throws Exception {
        // 移除指定的⽂件（存在）
        minIoTemplate.removeObject("mqxu", "img/test.jpg");
    }
}