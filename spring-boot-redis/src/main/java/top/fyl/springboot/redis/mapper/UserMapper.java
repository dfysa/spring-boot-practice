package top.fyl.springboot.redis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.fyl.springboot.redis.entity.User;

/**
 * @author dfysa
 * @data 2024/10/21 下午4:26
 * @description
 */
public interface UserMapper extends BaseMapper<User> {

    default User getByPhone(String phone){
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }
}