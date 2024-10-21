package top.fyl.springboot.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fyl.springboot.redis.entity.User;
import top.fyl.springboot.redis.vo.UserInfoVO;
import top.fyl.springboot.redis.vo.UserLoginVO;

/**
 * @author dfysa
 * @data 2024/10/21 下午4:27
 * @description
 */
public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param phone 电话
     * @param code 验证码
     * @return {@link UserLoginVO}
     */
    UserLoginVO loginByPhone(String phone, String code);

    /**
     * 检查⽤户是否启⽤
     *
     * @param userId ⽤户 ID
     * @return boolean
     */
    boolean checkUserEnabled(Long userId);
    /**
     * 获取⽤户信息
     *
     * @param userId ⽤户 ID
     * @return {@link UserInfoVO}
     */
    UserInfoVO userInfo(Long userId);
}
