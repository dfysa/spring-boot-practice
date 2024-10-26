package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Answer;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.entity.User;
import top.fyl.springboot.database.mapper.AnswerMapper;
import top.fyl.springboot.database.mapper.QuestionMapper;
import top.fyl.springboot.database.mapper.UserMapper;
import top.fyl.springboot.database.service.EmailService;
import top.fyl.springboot.database.service.UserService;
import top.fyl.springboot.database.util.JwtUtil;
import top.fyl.springboot.database.util.MD5Util;

import java.util.HashMap;
import java.util.List;
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
    private EmailService emailService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private MD5Util md5Util;

    @Override
    public List<Question> getQuestionsByUserId(int userId) {
        return questionMapper.findQuestionsByUserId(userId);
    }

    @Override
    public List<Answer> getAnswersByUserId(int userId) {
        return answerMapper.findAnswersByUserId(userId);
    }


    public UserServiceImpl( EmailService emailService) {

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



    // 注册用户
    @Override
    public void register(User user) {
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        String encryptedPassword = MD5Util.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
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
    public void updateUserInfo(User user) {
        // 先根据用户 ID 查询当前信息
        User existingUser = userMapper.findById(user.getId());

        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 仅更新传递了的字段
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            // 如果传入了新密码，进行 MD5 加密后再更新
            String encryptedPassword = md5Util.encrypt(user.getPassword());
            existingUser.setPassword(encryptedPassword);
        }
        if (user.getAccountname() != null) {
            existingUser.setAccountname(user.getAccountname());
        }

        userMapper.updateUser(existingUser); // 假设你有一个更新用户信息的 SQL 方法
    }

    @Override
    public void updateAvatar(int userId, String avatarUrl) {
        userMapper.updateAvatar(userId, avatarUrl); // 调用 avatar 更新 SQL
    }

    // 新增验证用户密码的方法
    @Override
    public boolean verifyPassword(int userId, String inputPassword) {
        // 从数据库中查找用户的加密密码
        String storedPassword = userMapper.findPasswordById(userId);

        if (storedPassword != null) {
            // 对输入的密码进行加密并与存储的密码进行比较
            String encryptedInputPassword = md5Util.encrypt(inputPassword);
            return storedPassword.equals(encryptedInputPassword);
        }
        return false;
    }





//    @Override
//    public void sendVerificationCode(String email) {
//        // 直接调用 EmailService 的发送方法
//        emailService.sendVerificationEmail(email);
//    }

//    @Override
//    public boolean verifyCode(String email, String code) {
//        String redisCode = redisTemplate.opsForValue().get(email);
//        return code.equals(redisCode);
//    }
}
