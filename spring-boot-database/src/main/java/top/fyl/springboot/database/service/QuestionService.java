package top.fyl.springboot.database.service;

import top.fyl.springboot.database.entity.Question;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:40
 * @description
 */
public interface QuestionService {
    List<Question> getAllQuestions();
    List<Question> searchQuestionsByName(String name);
    void addQuestion(Question question);
    List<Question> getAllQuestionsWithAnswers();

}
