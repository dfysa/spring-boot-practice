package top.fyl.springboot.configure.util;


import cn.hutool.jwt.JWTUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import top.fyl.springboot.configure.config.JwtConfig;

import java.util.Date;
import java.util.Map;

/**
 * @author dfysa
 * @data 23/9/2024 下午4:20
 * @description
 */
@Component
public class JwtUtil {
    @Resource
    private JwtConfig jwtConfig;

    public String generateToken (Map<String,Object>claims){
        claims.put("exp",new Date(System.currentTimeMillis()+jwtConfig.getExpiration()));
        return JWTUtil.createToken(claims, jwtConfig.getSecret().getBytes());
    }

    public boolean validateToken (String token){
        return JWTUtil.verify(token, jwtConfig.getSecret().getBytes());
    }



}
