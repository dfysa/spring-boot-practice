package top.fyl.springboot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.fyl.springboot.mp.entity.Course;
import top.fyl.springboot.mp.entity.StudentCourse;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/14 下午3:38
 * @description
 */
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {
    @Select("SELECT c.* FROM course c INNER JOIN student_course sc ON c.id = sc.course_id WHERE sc.student_id = #{studentId}")
    List<Course> selectCoursesByStudentId(Long studentId);
}
