package top.fyl.springboot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.fyl.springboot.mp.entity.User;

/**
 * @author dfysa
 * @data 2024/10/14 下午1:50
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
