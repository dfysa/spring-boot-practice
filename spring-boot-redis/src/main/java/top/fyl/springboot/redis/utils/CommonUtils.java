package top.fyl.springboot.redis.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dfysa
 * @data 2024/10/21 下午2:54
 * @description
 */
public class CommonUtils {

    /*
    * 生成四位随机整数验证码
    *
    * @return 验证码
    * */
    public static int generateCode(){
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }
}
