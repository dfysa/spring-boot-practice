package top.fyl.springboot.thymeleaf.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author dfysa
 * @data 2024/9/9 下午2:54
 * @description
 */
@Getter
@Setter
@ToString
@TableName("task")
public class Task {

    @TableId(value = "id", type = IdType.AUTO)  // 指定主键策略
    private Long id;
    private String description;
    private boolean completed;
    private int priority;
    private LocalDate deadline;



}