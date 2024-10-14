package top.fyl.springboot.mp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.mp.entity.Clazz;
import top.fyl.springboot.mp.service.ClazzService;

/**
 * @author dfysa
 * @data 2024/10/14 下午3:44
 * @description
 */
@RestController
@RequestMapping("/clazz")
@AllArgsConstructor
public class ClazzController {
    private final ClazzService clazzService;
    /**
     * 获取班级和对应⽼师信息
     */
    @GetMapping("/{id}")
    public Clazz getClazzWithTeacher(@PathVariable Long id) {
//        return clazzService.getClazzWithTeacher(id);
        return clazzService.getClazzWithDetails(id);
    }
//    @GetMapping("/{id}")
//    public Clazz getClazzWithDetails(@PathVariable Long id) {
//
//    }
}
