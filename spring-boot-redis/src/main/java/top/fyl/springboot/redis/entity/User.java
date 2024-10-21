package top.fyl.springboot.redis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/10/21 下午4:10
 * @description
 */
@Data
@TableName("t_user")
public class User {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;
    private String phone;
    private String avatar;
    private String nickname;
    private Integer gender;
    private Integer bonus;
    private String remark;
    /**
     * @see top.mqxu.springboot.redis.enums.AccountStatusEnum
     */
    private Integer enabled;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
