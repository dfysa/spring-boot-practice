package top.fyl.springboot.exception.handler;

/**
 * @author dfysa
 * @data 2024/10/15 下午1:44
 * @description
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fyl.springboot.exception.enums.ErrorCode;
import top.fyl.springboot.exception.exception.ServerException;
import top.fyl.springboot.exception.result.Result;
@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {

    /**
     * 处理自定义异常（ServerException）
     * @param exception 自定义的业务异常
     * @return 统一封装的错误响应
     */
    // 捕获 ServerException 异常
    @ExceptionHandler(ServerException.class)
    public Result<String> handleException(ServerException exception) {
        // 返回自定义错误信息
        return Result.error(exception.getCode(), exception.getMsg());
    }

    /**
     * 处理参数绑定异常（BindException）
     * @param exception 参数绑定异常
     * @return 统一封装的错误响应
     */
    // 捕获参数校验错误异常
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException exception) {
        // 获取校验错误的字段信息
        FieldError fieldError = exception.getFieldError();
        // 确保 fieldError 不为空
        assert fieldError != null;
        // 返回校验错误信息
        return Result.error(fieldError.getDefaultMessage());
    }

    /**
     * 处理其他未捕获的异常
     * @param ex 异常对象
     * @return 统一封装的错误响应
     */
    // 捕获所有其他类型的异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        // 打印错误日志
        log.error(ex.getMessage(), ex);
        // 返回通用的服务器内部错误信息
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}