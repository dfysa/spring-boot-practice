package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.User;
import top.fyl.springboot.database.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:35
 * @description
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());

        // 生成 Token
        String token = userService.generateToken(loginUser);

        // 构建响应数据
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", loginUser);
        responseData.put("token", token);

        return ResponseResult.builder()
                .code(200)
                .msg("登录成功")
                .data(responseData)
                .build();
    }


    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        userService.register(user);
        return ResponseResult.builder()
                .code(200)
                .msg("注册成功")
                .build();
    }

    // 新增根据 userId 查询用户信息的接口
    @GetMapping("/{userId}")
    public ResponseResult getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseResult.builder()
                    .code(404)
                    .msg("用户未找到")
                    .build();
        }
        return ResponseResult.builder()
                .code(200)
                .msg("查询成功")
                .data(user)
                .build();
    }
}
