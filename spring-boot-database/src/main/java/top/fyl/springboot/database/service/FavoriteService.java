package top.fyl.springboot.database.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Favorite;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午9:25
 * @description
 */
@Service
public interface FavoriteService {
    boolean toggleFavorite(int userId, int questionId);

    // 根据用户ID获取所有收藏记录
    List<Favorite> getFavoritesByUserId(int userId);
}
