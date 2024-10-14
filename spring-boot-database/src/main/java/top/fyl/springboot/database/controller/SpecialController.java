package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Special;
import top.fyl.springboot.database.mapper.SpecialMapper;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午4:43
 * @description
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/zhihu")
public class SpecialController {
    @Resource
    private SpecialMapper specialMapper;
    @GetMapping("/specials")
    public ResponseResult getSpecials(){
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(specialMapper.findAll())
                .build();
    }
}