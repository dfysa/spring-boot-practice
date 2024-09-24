package top.fyl.springboot.configure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.fyl.springboot.configure.entity.Special;

import java.util.List;

/**
 * @author dfysa
 * @data 23/9/2024 下午4:11
 * @description
 */
@Mapper
public interface SpecialMapper {
    @Select("select * from list ORDER BY updated DESC")
    List<Special> findAll();
}
