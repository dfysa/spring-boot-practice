package top.fyl.springboot.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.fyl.springboot.redis.cache.TokenStoreCache;
import top.fyl.springboot.redis.config.RedisCache;
import top.fyl.springboot.redis.config.RedisKeys;
import top.fyl.springboot.redis.entity.User;
import top.fyl.springboot.redis.enums.AccountStatusEnum;
import top.fyl.springboot.redis.enums.ErrorCode;
import top.fyl.springboot.redis.exception.ServerException;
import top.fyl.springboot.redis.mapper.UserMapper;
import top.fyl.springboot.redis.service.UserService;
import top.fyl.springboot.redis.utils.JwtUtil;
import top.fyl.springboot.redis.vo.UserInfoVO;
import top.fyl.springboot.redis.vo.UserLoginVO;

/**
 * @author dfysa
 * @data 2024/10/21 下午4:28
 * @description
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final RedisCache redisCache;
    private final TokenStoreCache tokenStoreCache;
    @Override
    public UserLoginVO loginByPhone(String phone, String code) {
        // 获取验证码cacheKey
        String smsCacheKey = RedisKeys.getSmsKey(phone);
        // 从redis中获取验证码
        Integer redisCode = (Integer) redisCache.get(smsCacheKey);
        // 校验验证码合法性
        if (ObjectUtils.isEmpty(redisCode) || !redisCode.toString().equals
                (code)) {
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        // 删除⽤过的验证码
        redisCache.delete(smsCacheKey);
        // 根据⼿机号获取⽤户
        User user = baseMapper.getByPhone(phone);
        // 判断⽤户是否注册过，如果user为空代表未注册，进⾏注册。否则开启登录流程
        if (ObjectUtils.isEmpty(user)) {
            log.info("⽤户不存在，创建⽤户, phone: {}", phone);
            user = new User();
            user.setNickname(phone);
            user.setPhone(phone);
            user.setAvatar("https://my-bucket-621.oss-cn-beijing.aliyuncs.com/bird/bird1.png");
                    user.setGender(0);
            user.setEnabled(AccountStatusEnum.ENABLED.getValue());
            user.setBonus(100);
            user.setDeleteFlag(0);
            user.setRemark("这个⼈很懒，什么都没有写");
            baseMapper.insert(user);
        }
        // ⽤户被禁⽤
        if (!user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue()
        )) {
            throw new ServerException(ErrorCode.ACCOUNT_DISABLED);
        }
        // 构造token
        String accessToken = JwtUtil.createToken(user.getPkId());
        // 构造登陆返回vo
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setPkId(user.getPkId());
        userLoginVO.setPhone(user.getPhone());
        userLoginVO.setAccessToken(accessToken);
        tokenStoreCache.saveUser(accessToken, userLoginVO);
        return userLoginVO;
    }

    @Override
    public boolean checkUserEnabled(Long userId) {
        User user = baseMapper.selectById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return false;
        }
        return user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue());
    }
    @Override
    public UserInfoVO userInfo(Long userId) {
        // 查询数据库
        User user = baseMapper.selectById(userId);
        if (user == null) {
            log.error("⽤户不存在, userId: {}", userId);
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return userInfoVO;
    }
}


