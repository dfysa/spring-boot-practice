package top.fyl.springboot.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.fyl.springboot.database.entity.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午9:24
 * @description
 */
@Mapper
public interface FavoriteMapper {

    void insertFavorite(Favorite favorite);

    Favorite findFavoriteIncludeDeleted(@Param("userId") int userId, @Param("questionId") int questionId);

    void deleteFavorite(@Param("userId") int userId, @Param("questionId") int questionId);

    void restoreFavorite(@Param("userId") int userId, @Param("questionId") int questionId);

    // 根据用户ID查询收藏的所有问题
    List<Favorite> findFavoritesByUserId(@Param("userId") int userId);

}