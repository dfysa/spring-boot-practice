package top.fyl.springboot.database.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.springboot.database.entity.Course;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 2024/10/12 下午3:32
 * @description
 */
@SpringBootTest
@Slf4j
public class CourseMapperTest {

    @Resource
    private CourseMapper courseMapper;

    @Test
    void getCourseWithStudents() {
        Course course = courseMapper.getCourseWithStudents(20001);
        log.info("Course: {}, {}", course.getCourseName(), course.getCourseId());

        course.getStudents().forEach(student -> {
            log.info("Student: {}, {}",
                    student.getStudentName(), student.getHometown()

            );
        });
    }
}