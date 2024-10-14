package top.fyl.springboot.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.fyl.springboot.database.entity.Student;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/8 下午4:27
 * @description
 */

public interface StudentMapper {
    int insert(Student student);
    Student findStudentById(int studentId);
    int updateById(Student student);
    int deleteById(int studentId);
    int batchInsert(@Param("students")List<Student>students);
    int batchDelete(@Param("idList")List<Integer>ids);
    List<Student> selectByDynamicSql(Student student);

    // 更新单个学生的方法
    int batchupdate(Student student);

    Student getStudentManyToOne(int studentId);
     Student getStudent(int studentId);

}

