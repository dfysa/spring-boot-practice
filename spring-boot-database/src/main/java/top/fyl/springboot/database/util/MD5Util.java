package top.fyl.springboot.database.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dfysa
 * @data 2024/10/16 下午2:44
 * @description
 */
@Component
public class MD5Util {
    // 将字符串转换为MD5加密后的形式
    public static String encrypt(String password) {
        try {
            // 获取MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将密码转换为字节数组并进行摘要计算
            byte[] digest = md.digest(password.getBytes());

            // 将字节数组转换为16进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }
}
