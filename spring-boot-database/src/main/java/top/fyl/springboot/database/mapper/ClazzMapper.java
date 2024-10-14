package top.fyl.springboot.database.mapper;

import top.fyl.springboot.database.entity.Clazz;
import top.fyl.springboot.database.entity.Teacher;

/**
 * @author dfysa
 * @data 2024/10/12 下午1:49
 * @description
 */
public interface ClazzMapper {
    //根据id查询班级信息（一对多查询出该班级的所有学生信息）
    Clazz getClazzById(int clazzId);
    Teacher getTeacherByClazzId(int clazzId);
    Clazz getClazz(int clazzId);


}
