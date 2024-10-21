package top.fyl.springboot.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.fyl.springboot.database.entity.Answer;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.entity.User;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:34
 * @description
 */
@Mapper
public interface UserMapper {
    User findByUsername(String username);
    void insertUser(User user);
    // 新增根据 userId 查询用户信息的方法
    User findById(int userId);



}
