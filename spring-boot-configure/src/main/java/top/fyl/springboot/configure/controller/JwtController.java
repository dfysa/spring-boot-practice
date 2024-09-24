package top.fyl.springboot.configure.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dfysa
 * @data 23/9/2024 下午4:22
 * @description
 */
@RestController
@RequestMapping("/api/jwt")
@AllArgsConstructor
public class JwtController {
    @Resource
    private JwtUtil jwtUtil;
    /**
     * ⽣成JWT的接⼝
     *
     * @param username ⽤户名
     * @return 返回⽣成的JWT
     */
    @PostMapping("/generate")
    public Map<String, String> generateToken(@RequestParam String username
    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        String token = jwtUtil.generateToken(claims);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
    /**
     * 验证JWT的接⼝
     * @param token JWT字符串
     * @return 返回验证结果
     */
    @PostMapping("/validate")
    public Map<String, Object> validateToken(@RequestParam String token) {
        boolean isValid = jwtUtil.validateToken(token);
        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);

        return response;
    }
}
