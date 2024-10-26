package top.fyl.springboot.database.service;

import cn.hutool.db.Page;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.util.PaginationUtil;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:40
 * @description
 */
@Service
public interface QuestionService {
    List<Question> getAllQuestions();
    List<Question> searchQuestionsByName(String name);
    void addQuestion(Question question);
      PaginationUtil<Question> getAllQuestionsWithAnswers(Page page, String title);
    PaginationUtil<Question> getAllQuestionsWithAnswers(Page page);
    Question getQuestionById(int id);
    void updateQuestionById(Question question);
    // 新增逻辑删除方法
    void deleteQuestionById(long id, long userId);
}
