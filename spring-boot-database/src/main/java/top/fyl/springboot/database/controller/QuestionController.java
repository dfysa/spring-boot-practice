package top.fyl.springboot.database.controller;

import cn.hutool.db.Page;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.service.QuestionService;
import top.fyl.springboot.database.util.JwtUtil;
import top.fyl.springboot.database.util.PaginationUtil;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:35
 * @description
 */

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @Resource
    private JwtUtil jwtUtil; // 注入 JwtUtil 用于解析 Token

//    @GetMapping("/getallonlyquestions")
//    public ResponseResult getAllQuestion() {
////        return ResponseResult.success(questionService.getAllQuestions());
//        return ResponseResult.builder()
//                .code(200)
//                .msg("数据获取成功")
//                .data(questionService.getAllQuestions())
//                .build();
//    }

    /**
     * 根据问题标题模糊查询
     */
    @GetMapping("/search")
    public ResponseResult searchQuestions(@RequestParam String name) {
//        return ResponseResult.success(questionService.searchQuestionsByName(name));
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(questionService.searchQuestionsByName(name))
                .build();
    }

    @PostMapping("/add")
    public ResponseResult addQuestion(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Question question) {

        // 去掉 "Bearer " 前缀，获取纯 Token
        String token = authorizationHeader.replace("Bearer ", "");

        // 从 Token 中解析用户 ID
        String userId = jwtUtil.extractUserId(token);

        // 设置问题的 userId
        question.setUserId(Integer.parseInt(userId));

        // 添加问题
        questionService.addQuestion(question);

        return ResponseResult.builder()
                .code(200)
                .msg("问题添加成功")
                .data("问题添加成功")
                .build();
    }

    @GetMapping("/getall")
    public ResponseResult getAllQuestions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page hutoolPage = new Page(page, size);
        PaginationUtil<Question> result = questionService.getAllQuestionsWithAnswers(hutoolPage);

        String message = result.getRecords().isEmpty() ? "无数据" : "数据获取成功";

        return ResponseResult.builder()
                .code(200)
                .msg(message)
                .data(result)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseResult getQuestionById(@PathVariable int id){
        Question question=questionService.getQuestionById(id);
        if (question==null){
            return ResponseResult.builder()
                    .code(404)
                    .msg("找不到问题")
                    .build();
        }else{
            return ResponseResult.builder()
                    .code(200)
                    .msg("问题查询成功")
                    .data(question)
                    .build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseResult updateQuestion(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable int id,
            @RequestBody Question question) {

        // 解析 Token，确保用户有权限
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtUtil.extractUserId(token);

        // 检查当前用户是否是问题的发布者
        Question existingQuestion = questionService.getQuestionById(id);
        if (existingQuestion == null) {
            return ResponseResult.builder()
                    .code(404)
                    .msg("问题不存在")
                    .build();
        }
        if (!String.valueOf(existingQuestion.getUserId()).equals(userId)) {
            return ResponseResult.builder()
                    .code(403)
                    .msg("没有权限修改此问题")
                    .build();
        }

        // 设置问题 ID，确保修改的是指定问题
        question.setId(id);

        // 执行更新
        questionService.updateQuestionById(question);

        return ResponseResult.builder()
                .code(200)
                .msg("问题修改成功")
                .build();
    }


}
