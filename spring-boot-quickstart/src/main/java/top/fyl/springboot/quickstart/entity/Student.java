package top.fyl.springboot.quickstart.entity;

/**
 * @author dfysa
 * @data 2024/9/5 下午10:33
 * @description
 */
public class Student {
    private Long id;
    private String name;
    private Grade grade;  // 引用 Grade 实体

    public Student(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    // 获取成绩值
    public double getGradeValue() {
        return grade.getValue();
    }
}
