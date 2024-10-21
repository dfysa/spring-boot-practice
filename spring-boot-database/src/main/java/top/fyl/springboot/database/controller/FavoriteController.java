package top.fyl.springboot.database.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.database.comoon.ResponseResult;
import top.fyl.springboot.database.entity.Favorite;
import top.fyl.springboot.database.service.FavoriteService;
import top.fyl.springboot.database.util.JwtUtil;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午9:26
 * @description
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteService;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/toggle")
    public ResponseResult toggleFavorite(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam int questionId) {

        String token = authorizationHeader.replace("Bearer ", "");
        int userId = Integer.parseInt(jwtUtil.extractUserId(token));

        boolean isFavorited = favoriteService.toggleFavorite(userId, questionId);
        String message = isFavorited ? "收藏成功" : "取消收藏成功";

        return ResponseResult.builder()
                .code(200)
                .msg(message)
                .build();
    }


    // 根据用户 ID 获取收藏的所有问题
    @GetMapping("/user")
    public ResponseResult getFavoritesByUserId(
            @RequestHeader("Authorization") String authorizationHeader) {

        // 解析用户 ID
        String token = authorizationHeader.replace("Bearer ", "");
        int userId = Integer.parseInt(jwtUtil.extractUserId(token));

        // 查询收藏记录
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);

        String message = favorites.isEmpty() ? "无收藏记录" : "收藏记录获取成功";
        return ResponseResult.builder()
                .code(200)
                .msg(message)
                .data(favorites)
                .build();
    }
}
