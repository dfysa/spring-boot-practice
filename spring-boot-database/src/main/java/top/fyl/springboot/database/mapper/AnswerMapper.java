package top.fyl.springboot.database.mapper;

import top.fyl.springboot.database.entity.Answer;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:34
 * @description
 */
public interface AnswerMapper {
    List<Answer> findByQuestionId(int relatedQuestionId); // 修改为 relatedQuestionId
    List<Answer> findByParentId(Long parentId);
    void insertAnswer(Answer answer);
}
