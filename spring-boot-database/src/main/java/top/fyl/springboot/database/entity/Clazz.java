package top.fyl.springboot.database.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午1:34
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clazz {
    private Integer clazzId;

    private String clazzName;

    private String teacherId;
    // 在以防中声明另一方的对象
    private Teacher teacher;
    //在一方中声明多方的集合
    private List<Student> students;
}
