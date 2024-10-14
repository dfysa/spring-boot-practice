package top.fyl.springboot.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午2:43
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private String courseName;
    private Integer courseId;
    private List<Student> students;
}
