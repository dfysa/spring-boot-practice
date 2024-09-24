package top.fyl.zhihu.api.comoon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dfysa
 * @data 14/9/2024 下午2:25
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult  {
    private Integer code;
    private String msg;
    private Object data;
}