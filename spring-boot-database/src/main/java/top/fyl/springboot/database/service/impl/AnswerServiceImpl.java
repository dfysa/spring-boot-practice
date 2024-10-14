package top.fyl.springboot.database.service.impl;

import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Answer;
import top.fyl.springboot.database.mapper.AnswerMapper;
import top.fyl.springboot.database.service.AnswerService;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;

    public AnswerServiceImpl(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public List<Answer> findByQuestionId(Long relatedQuestionId) { // 修改为 relatedQuestionId
        return answerMapper.findByQuestionId(Math.toIntExact(relatedQuestionId));
    }

    @Override
    public List<Answer> findByParentId(Long parentId) {
        return answerMapper.findByParentId(parentId);
    }

    @Override
    public void addAnswer(Answer answer) {
        if (answer.getParentId() != null) {
            // 处理子回答的情况
            if (answer.getRelatedQuestionId() == 0) { // 如果没有提供 relatedQuestionId，可以抛出异常或返回错误信息
                throw new IllegalArgumentException("When providing parentId, relatedQuestionId must also be provided.");
            }
            answerMapper.insertAnswer(answer); // 这里 insertAnswer 会将 parentId 和 relatedQuestionId 一起插入
        } else {
            // 处理新回答的情况
            answerMapper.insertAnswer(answer); // 这里同样会插入相关信息
        }
    }

}
