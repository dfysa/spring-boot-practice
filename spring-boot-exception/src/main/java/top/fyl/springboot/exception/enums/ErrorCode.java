package top.fyl.springboot.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dfysa
 * @data 2024/10/15 下午1:38
 * @description
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "登录失效，请重新登录"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试");
    private final int code;
    private final String msg;
}