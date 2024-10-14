package top.fyl.springboot.mp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.fyl.springboot.mp.entity.Clazz;
import top.fyl.springboot.mp.entity.Student;
import top.fyl.springboot.mp.entity.Teacher;
import top.fyl.springboot.mp.mapper.ClazzMapper;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/14 下午3:40
 * @description
 */
@Service
@AllArgsConstructor
public class ClazzService {
    private final ClazzMapper clazzMapper;
    /**
     * 根据班级ID获取班级和对应⽼师(包含教师信息)
     */
    public Clazz getClazzWithTeacher(Long clazzId) {
        Clazz clazz = clazzMapper.selectById(clazzId);
        if (clazz != null) {
            Teacher teacher = clazzMapper.selectTeacherByClazzId(clazz.getTeacherId());
            clazz.setTeacher(teacher);
        }
        return clazz;
    }

    /**
     * 根据班级ID获取班级信息，包括教师和学生信息
     */
    public Clazz getClazzWithDetails(Long clazzId) {
        // 查询班级基本信息
        Clazz clazz = clazzMapper.selectById(clazzId);
        if (clazz != null) {
            // 查询教师信息
            Teacher teacher = clazzMapper.selectTeacherByClazzId(clazz.getTeacherId());
            clazz.setTeacher(teacher);

            // 查询学生列表
            List<Student> students = clazzMapper.selectStudentsByClazzId(clazzId);
            clazz.setStudents(students);
        }
        return clazz;
    }

}
