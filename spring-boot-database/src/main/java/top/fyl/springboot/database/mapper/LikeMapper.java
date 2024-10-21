package top.fyl.springboot.database.mapper;

import org.apache.ibatis.annotations.*;
import top.fyl.springboot.database.entity.Like;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午3:41
 * @description
 */

@Mapper
public interface LikeMapper {

    /**
     * 插入点赞记录
     */
    void insertLike(Like like);

    /**
     * 查询点赞记录，包括逻辑删除的记录
     */
    Like findLikeIncludeDeleted(@Param("userId") int userId, @Param("targetId") int targetId, @Param("type") byte type);

    /**
     * 逻辑删除点赞记录
     */
    void deleteLike(@Param("userId") int userId, @Param("targetId") int targetId, @Param("type") byte type);

    /**
     * 恢复逻辑删除的点赞记录
     */
    void restoreLike(@Param("userId") int userId, @Param("targetId") int targetId, @Param("type") byte type);

    /**
     * 查询点赞了我问题的记录
     */
    List<Like> findLikesForMyQuestions(@Param("userId") int userId);

    /**
     * 查询点赞了我回答的记录
     */
    List<Like> findLikesForMyAnswers(@Param("userId") int userId);
}
