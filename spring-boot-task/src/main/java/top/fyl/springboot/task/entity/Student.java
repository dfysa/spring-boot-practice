package top.fyl.springboot.task.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author dfysa
 * @data 2024/10/28 下午4:21
 * @description
 */
@Data
@TableName("t_student")
public class Student {
    @ExcelProperty("学生学号")
    private Integer studentId;
    @ExcelIgnore
    private Integer clazzId;
    @ExcelProperty("学生姓名")
    private String studentName;
    @ExcelProperty("家乡")
    private String hometown;
    @ExcelIgnore
    private Date birthday;
}
