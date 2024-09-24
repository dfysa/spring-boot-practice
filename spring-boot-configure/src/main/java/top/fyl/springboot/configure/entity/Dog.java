package top.fyl.springboot.configure.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
/**
 * @author dfysa
 * @data 23/9/2024 下午6:02
 * @description
 */
@Data
@Component
public class Dog {
    @Value("${student.dog.name}")
    private String name;
    @Value("${student.dog.age}")
    private Integer age;
}