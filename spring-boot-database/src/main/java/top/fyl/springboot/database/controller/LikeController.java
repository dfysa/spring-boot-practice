package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Like;
import top.fyl.springboot.database.service.LikeService;
import top.fyl.springboot.database.util.JwtUtil;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午3:49
 * @description
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    @Resource
    private LikeService likeService;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/toggle")
    public ResponseResult toggleLike(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam int targetId,
            @RequestParam byte type) {

        String token = authorizationHeader.replace("Bearer ", "");
        int userId = Integer.parseInt(jwtUtil.extractUserId(token));  // 解析用户ID

        boolean isLiked = likeService.toggleLike(userId, targetId, type);
        String message = isLiked ? "点赞成功" : "取消点赞成功";

        return ResponseResult.builder()
                .code(200)
                .msg(message)
                .build();
    }

    @GetMapping("/questions")
    public List<Like> getLikesForMyQuestions(
            @RequestHeader("Authorization") String authorizationHeader) {
        int userId = Integer.parseInt(jwtUtil.extractUserId(authorizationHeader));
        return likeService.getLikesForMyQuestions(userId);
    }

    @GetMapping("/answers")
    public List<Like> getLikesForMyAnswers(
            @RequestHeader("Authorization") String authorizationHeader) {
        int userId = Integer.parseInt(jwtUtil.extractUserId(authorizationHeader));
        return likeService.getLikesForMyAnswers(userId);
    }



}
