package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Like;
import top.fyl.springboot.database.mapper.LikeMapper;
import top.fyl.springboot.database.service.LikeService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dfysa
 * @data 2024/10/16 下午3:40
 * @description
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Override
    public boolean toggleLike(int userId, int targetId, byte type) {
        // 检查是否有点赞记录（包括逻辑删除的）
        Like existingLike = likeMapper.findLikeIncludeDeleted(userId, targetId, type);

        if (existingLike != null) {
            if (existingLike.isLdeleteFlag()) {
                // 存在已逻辑删除的记录，恢复点赞（将 ldelete_flag 设置为 0）
                likeMapper.restoreLike(userId, targetId, type);
                return true;  // 表示点赞成功
            } else {
                // 已点赞，取消点赞
                likeMapper.deleteLike(userId, targetId, type);
                return false;  // 表示已取消点赞
            }

        } else {
            // 未点赞，执行插入
            Like newLike = Like.builder()
                    .userId(userId)
                    .targetId(targetId)
                    .type(type)
                    .ldeleteFlag(false).build();
            likeMapper.insertLike(newLike);
            return true;  // 表示成功点赞
        }
    }

    /**
     * 获取点赞了我问题的记录
     */
    @Override
    public List<Like> getLikesForMyQuestions(int userId) {
        return likeMapper.findLikesForMyQuestions(userId);
    }

    /**
     * 获取点赞了我回答的记录
     */
    @Override
    public List<Like> getLikesForMyAnswers(int userId) {
        return likeMapper.findLikesForMyAnswers(userId);
    }

}
