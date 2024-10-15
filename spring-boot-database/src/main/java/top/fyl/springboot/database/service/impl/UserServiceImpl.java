package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.User;
import top.fyl.springboot.database.mapper.UserMapper;
import top.fyl.springboot.database.service.EmailService;
import top.fyl.springboot.database.service.UserService;
import top.fyl.springboot.database.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:35
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private EmailService emailService;

    @Resource
    private JwtUtil jwtUtil;




    public UserServiceImpl(RedisTemplate<String, String> redisTemplate, EmailService emailService) {
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("用户名或密码错误");
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId()); // 存储用户ID
        return jwtUtil.generateToken(claims);
    }



    @Override
    public void register(User user) {
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(int userId) {
        User user = userMapper.findById(userId);
        if (user != null) {
            // 不返回密码
            user.setPassword(null);
        }
        return user;
    }


    @Override
    public void sendVerificationCode(String email) {
        // 直接调用 EmailService 的发送方法
        emailService.sendVerificationEmail(email);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        String redisCode = redisTemplate.opsForValue().get(email);
        return code.equals(redisCode);
    }
}
