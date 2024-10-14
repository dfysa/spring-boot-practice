package top.fyl.springboot.database.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import top.fyl.springboot.database.config.JwtConfig;

import java.util.Map;

@Component
public class JwtUtil {

    @Resource
    private JwtConfig jwtConfig;

    // 生成 Token
    public String generateToken(Map<String, Object> claims) {
        claims.put("exp", System.currentTimeMillis() + jwtConfig.getExpiration());
        return JWTUtil.createToken(claims, jwtConfig.getSecret().getBytes());
    }

    // 验证 Token
    public boolean validateToken(String token) {
        return JWTUtil.verify(token, jwtConfig.getSecret().getBytes());
    }

    // 从 Token 中提取用户 ID
    public String extractUserId(String token) {
        JWT jwt = JWTUtil.parseToken(token);  // 解析 Token
        Map<String, Object> payload = jwt.getPayloads();  // 获取负载部分
        Object userId = payload.get("id");  // 获取用户 ID

        if (userId == null) {
            throw new RuntimeException("无效的 Token");
        }
        return userId.toString();
    }
}
