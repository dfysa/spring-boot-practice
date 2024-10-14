package top.fyl.springboot.database.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dfysa
 * @data 2024/10/12 下午1:36
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Teacher {
    private Integer teacherId;

    private String teacherName;

    private Integer clazzId;

    private Clazz clazz;
}
