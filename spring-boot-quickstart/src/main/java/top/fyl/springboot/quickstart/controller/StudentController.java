package top.fyl.springboot.quickstart.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.quickstart.service.StudentService;

import java.util.OptionalDouble;

/**
 * @author dfysa
 * @data 2024/9/5 下午10:34
 * @description
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/grade/{id}")
    public OptionalDouble getStudentGrade(@PathVariable Long id) {
        return studentService.getStudentGrade(id);
    }

    @GetMapping("/average-grade")
    public double getAverageGrade() {
        return studentService.getAverageGrade();
    }
}