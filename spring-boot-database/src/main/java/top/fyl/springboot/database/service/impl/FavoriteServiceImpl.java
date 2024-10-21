package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Favorite;
import top.fyl.springboot.database.mapper.FavoriteMapper;
import top.fyl.springboot.database.service.FavoriteService;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午9:26
 * @description
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public boolean toggleFavorite(int userId, int questionId) {
        Favorite existingFavorite = favoriteMapper.findFavoriteIncludeDeleted(userId, questionId);

        if (existingFavorite != null) {
            if (existingFavorite.isDeleteFlag()) {
                favoriteMapper.restoreFavorite(userId, questionId);
                return true;
            } else {
                favoriteMapper.deleteFavorite(userId, questionId);
                return false;
            }
        } else {
            Favorite newFavorite = Favorite.builder()
                    .userId(userId)
                    .questionId(questionId)
                    .deleteFlag(false).build();
            favoriteMapper.insertFavorite(newFavorite);
            return true;
        }
    }

    @Override
    public List<Favorite> getFavoritesByUserId(int userId) {
        return favoriteMapper.findFavoritesByUserId(userId);
    }
}