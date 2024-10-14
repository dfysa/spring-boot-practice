package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Answer;
import top.fyl.springboot.database.service.AnswerService;
import top.fyl.springboot.database.util.JwtUtil;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:35
 * @description 回答控制器
 */
@RestController
@RequestMapping("/api/v1/answers")
public class AnswerController {

    @Resource
    private AnswerService answerService;


    @Resource
    private JwtUtil jwtUtil; // 注入 JwtUtil 用于解析 Token

    /**
     * 根据问题ID获取所有回答
     */
    @GetMapping("/question/{questionId}")
    public ResponseResult getAnswersByQuestionId(@PathVariable Long questionId) {
        List<Answer> answers = answerService.findByQuestionId(questionId);
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(answers)
                .build();
    }

    /**
     * 根据父回复ID获取子回复
     */
    @GetMapping("/reply/{parentId}")
    public ResponseResult getRepliesByParentId(@PathVariable Long parentId) {
        List<Answer> replies = answerService.findByParentId(parentId);
        String message = replies.isEmpty() ? "无数据" : "数据获取成功";

        return ResponseResult.builder()
                .code(200)
                .msg(message)
                .data(replies)
                .build();
    }

    /**
     * 添加新的回答
     */




    @PostMapping("/add")
    public ResponseResult addAnswer(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Answer answer) {
        // 去掉 "Bearer " 前缀，获取纯 Token
        String token = authorizationHeader.replace("Bearer ", "");

        // 从 Token 中解析用户 ID
        String userId = jwtUtil.extractUserId(token);

        // 设置问题的 userId
        answer.setUserId(Integer.parseInt(userId));

        // 添加问题
        answerService.addAnswer(answer);

        return ResponseResult.builder()
                .code(200)
                .msg("回答添加成功")
                .data("回答添加成功")
                .build();


    }

}