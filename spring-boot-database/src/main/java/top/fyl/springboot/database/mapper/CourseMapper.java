package top.fyl.springboot.database.mapper;

import top.fyl.springboot.database.entity.Course;
import top.fyl.springboot.database.entity.Teacher;

/**
 * @author dfysa
 * @data 2024/10/12 下午1:38
 * @description
 * 根据id查询教师信息，同时通过一对一查询出其管理班级的信息
 */
public interface CourseMapper {
    Course getCourseWithStudents(int courseId);
}
