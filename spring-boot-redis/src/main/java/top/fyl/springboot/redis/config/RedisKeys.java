package top.fyl.springboot.redis.config;

/**
 * @author dfysa
 * @data 2024/10/21 下午2:51
 * @description rediskey ,统一维护 redis中的key
 */
public class RedisKeys {
    public static String getSmsKey(String phone){
        return "sms:captcha:"+phone;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:" + accessToken;
    }
    /**
     * 获取⽤户 ID 密钥
     *
     * @param id id
     * @return {@link String}
     */
    public static String getUserIdKey(Long id) {
        return "sys:userId:" + id;
    }

}
