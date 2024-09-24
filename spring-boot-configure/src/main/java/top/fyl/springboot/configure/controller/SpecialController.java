package top.fyl.springboot.configure.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.entity.Special;
import top.fyl.springboot.configure.mapper.SpecialMapper;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/23 下午7:10
 * @description
 */
@RestController
public class SpecialController {
    @Resource
    private SpecialMapper specialMapper;
    @GetMapping("/specials")
    public List<Special> getSpecials() {
        return specialMapper.findAll();
    }
}