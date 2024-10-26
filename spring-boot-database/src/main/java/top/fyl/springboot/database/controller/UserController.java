package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Answer;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.entity.User;
import top.fyl.springboot.database.service.EmailService;
import top.fyl.springboot.database.service.UserService;
import top.fyl.springboot.database.util.JwtUtil;
import top.fyl.springboot.database.util.MD5Util;

import java.util.HashMap;
import java.util.List;
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

    @Resource
    private final EmailService emailService;

    @Resource
    private MD5Util md5Util;
    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {

        // MD5 加密用户输入的密码
        String encryptedPassword = md5Util.encrypt(user.getPassword());

        User loginUser = userService.login(user.getUsername(), encryptedPassword);

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

//    // 发送验证码
//    @PostMapping("/sendCode")
//    public String sendCode(@RequestParam String email) {
//        userService.sendVerificationCode(email);
//        return "验证码已发送";
//    }

    // 用户注册接口
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String email,
            @RequestParam String code,
            @RequestBody User user) {

        // 校验验证码
        if (!emailService.verifyCode(email, code)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误或已过期");
        }

        // 验证通过后，完成用户注册
        userService.register(user);
        return ResponseEntity.ok("注册成功");
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

    @GetMapping("/activities")
    public ResponseResult getUserActivities(
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "");
        int userId = Integer.parseInt(jwtUtil.extractUserId(token));

        List<Question> questions = userService.getQuestionsByUserId(userId);
        List<Answer> answers = userService.getAnswersByUserId(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("questions", questions);
        data.put("answers", answers);
        System.out.println(data);

        return ResponseResult.builder()
                .code(200)
                .msg("查询成功")
                .data(data)
                .build();
    }

    @PostMapping("/update")
    public ResponseResult updateUserInfo( @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User user) {
        String token = authorizationHeader.replace("Bearer ", "");
        int userId = Integer.parseInt(jwtUtil.extractUserId(token));
        user.setId(userId);
        userService.updateUserInfo(user);
        return ResponseResult.builder()
                .code(200)
                .msg("用户信息更新成功")
                .build();
    }

    @PostMapping("/update-avatar")
    public ResponseResult updateAvatar(@RequestParam int userId, @RequestParam String avatarUrl) {
        userService.updateAvatar(userId, avatarUrl);
        return ResponseResult.builder()
                .code(200)
                .msg("头像更新成功")
                .build();
    }

    @PostMapping("/validate-password")
    public ResponseResult validatePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, String> passwordData) {

        String token = authorizationHeader.replace("Bearer ", "");
        String inputPassword = passwordData.get("password");

        // 从 token 中提取 userId
        int userId = Integer.parseInt(jwtUtil.extractUserId(token));

        // 验证密码是否正确
        boolean isPasswordCorrect = userService.verifyPassword(userId, inputPassword);

        if (isPasswordCorrect) {
            return ResponseResult.builder()
                    .code(200)
                    .msg("密码验证成功")
                    .data(Map.of("valid", true))
                    .build();
        } else {
            return ResponseResult.builder()
                    .code(401)
                    .msg("密码验证失败")
                    .data(Map.of("valid", false))
                    .build();
        }
    }

}