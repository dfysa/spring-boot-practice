package top.fyl.springboot.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Like;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午3:39
 * @description
 */
@Service

public interface LikeService {
    // 点赞/取消点赞
    boolean toggleLike(int userId, int targetId, byte type);

    List<Like> getLikesForMyQuestions(int userId);

    List<Like> getLikesForMyAnswers(int userId);


}
