package top.fyl.springboot.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/10/28 下午3:34
 * @description
 */
@Data
@TableName("email_log")
public class EmailLog {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String recipient;
    private String subject;
    private String content;
    private LocalDateTime sentAt;




}
