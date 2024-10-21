package top.fyl.springboot.redis.result;

import lombok.Data;
import top.fyl.springboot.redis.enums.ErrorCode;


/**
 * @author dfysa
 * @data 2024/10/15 下午1:40
 * @description
 */
@Data  // Lombok 注解，自动生成 getter、setter、toString 等方法
public class Result<T> {
    // 状态码，默认 0 表示成功
    private int code = 0;
    // 响应消息，默认 "success"
    private String msg = "success";
    // 泛型数据载体，用于存储响应内容
    private T data;

    // 静态方法，返回成功的结果，不带数据
    public static <T> Result<T> ok() {
        // 调用另一个重载方法
        return ok(null);
    }

    // 静态方法，返回成功的结果，并带上数据
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        // 设置数据
        result.setData(data);
        return result;
    }

    // 静态方法，返回错误的结果，默认内部服务器错误
    public static <T> Result<T> error() {
        // 使用预定义错误码
        return error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 返回带自定义错误消息的结果
    public static <T> Result<T> error(String msg) {
        return error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    // 返回特定错误码的错误信息
    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    // 返回带状态码和消息的错误结果
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        // 设置状态码
        result.setCode(code);
        // 设置错误信息
        result.setMsg(msg);
        return result;
    }
}
