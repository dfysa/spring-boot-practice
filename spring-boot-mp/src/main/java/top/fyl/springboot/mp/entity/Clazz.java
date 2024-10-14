package top.fyl.springboot.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/14 下午3:36
 * @description
 */
@Data
public class Clazz {
    private Long id;
    private String name;
    private Long teacherId;
    // 声明数据表中不存在的数据 ⽤于⼀对⼀映射
    @TableField(exist = false)
    private Teacher teacher;

    // 声明数据表中不存在的数据 ⽤于⼀对⼀映射
    @TableField(exist = false)
    private List<Student> students;
}
