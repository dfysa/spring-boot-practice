package top.fyl.springboot.exception.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.exception.service.ExceptionService;

/**
 * @author dfysa
 * @data 2024/10/15 下午1:45
 * @description
 */
@RestController
public class ExceptionController {
    @Resource
    private ExceptionService exceptionService;
    @GetMapping("/articles/{id}")
    public void getArticle(@PathVariable("id") Integer id) {
        if (id == 1) {
            exceptionService.unAuthorizedError();
        } else {
            exceptionService.systemError();
        }
    }
}