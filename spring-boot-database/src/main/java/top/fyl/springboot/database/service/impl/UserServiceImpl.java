package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.User;
import top.fyl.springboot.database.mapper.UserMapper;
import top.fyl.springboot.database.service.UserService;
import top.fyl.springboot.database.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

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
    private JwtUtil jwtUtil; // 引入 JwtUtil

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("用户名或密码错误");
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId()); // 存储用户ID
        return jwtUtil.generateToken(claims); // 生成并返回 Token
    }


    @Override
    public void register(User user) {
        userMapper.insertUser(user);
    }
    @Override
    public User getUserById(int userId) {
        User user = userMapper.findById(userId);
        if (user != null) {
            user.setPassword(null); // 不返回密码
        }
        return user;
    }
}
