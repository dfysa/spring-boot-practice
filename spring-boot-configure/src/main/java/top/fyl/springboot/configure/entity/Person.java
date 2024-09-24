package top.fyl.springboot.configure.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

/**
 * @author dfysa
 * @data 23/9/2024 下午2:07
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private UUID id;
    private Number phone;
}