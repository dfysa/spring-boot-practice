package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.service.QuestionService;
import top.fyl.springboot.database.util.JwtUtil;

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
    public ResponseResult getAllQuestions() {
        List<Question> questions = questionService.getAllQuestionsWithAnswers();
        String message = questions.isEmpty() ? "无数据" : "数据获取成功";

        return ResponseResult.builder()
                .code(200)
                .msg(message)
                .data(questions)
                .build();
    }


}
