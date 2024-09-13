package top.fyl.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.quickstart.entity.Grade;
import top.fyl.springboot.quickstart.entity.Student;

import java.util.List;
import java.util.OptionalDouble;

/**
 * @author dfysa
 * @data 2024/9/5 下午10:34
 * @description
 */
@Service
public class StudentService {

    private final List<Student> students = List.of(
            new Student(1L, "Alice", new Grade(85.0)),
            new Student(2L, "Bob", new Grade(45.0)),
            new Student(3L, "Charlie", new Grade(75.0)),
            new Student(4L, "David", new Grade(55.0)),
            new Student(5L, "Eve", new Grade(90.0))
    );

    // 使用方法引用获取特定学生的成绩
    public OptionalDouble getStudentGrade(Long studentId) {
        return students.stream()
                .filter(student -> student.getId().equals(studentId))
                .mapToDouble(Student::getGradeValue)  // 方法引用用于 getGradeValue
                .findFirst();
    }

    // 使用方法引用计算平均成绩
    public double getAverageGrade() {
        return students.stream()
                .mapToDouble(Student::getGradeValue)  // 方法引用用于 getGradeValue
                .average()
                .orElse(0.0);
    }
}
