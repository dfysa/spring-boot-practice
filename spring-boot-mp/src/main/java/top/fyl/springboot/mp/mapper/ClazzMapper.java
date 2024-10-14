package top.fyl.springboot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.fyl.springboot.mp.entity.Clazz;
import top.fyl.springboot.mp.entity.Student;
import top.fyl.springboot.mp.entity.Teacher;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/14 下午3:38
 * @description
 */
public interface ClazzMapper extends BaseMapper<Clazz> {
    @Select("SELECT * FROM teacher WHERE id = #{teacherId}")
    Teacher selectTeacherByClazzId(Long teacherId);
    // 根据班级ID查询该班级的所有学生
    @Select("SELECT * FROM student WHERE clazz_id = #{clazzId}")
    List<Student> selectStudentsByClazzId(@Param("clazzId") Long clazzId);
}