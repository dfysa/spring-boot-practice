package top.fyl.springboot.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.fyl.springboot.task.entity.Student;

/**
 * @author dfysa
 * @data 2024/10/28 下午4:24
 * @description
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
