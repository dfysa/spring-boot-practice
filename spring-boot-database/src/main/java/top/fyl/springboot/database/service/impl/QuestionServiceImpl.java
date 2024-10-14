package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Answer;

import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.mapper.AnswerMapper;
import top.fyl.springboot.database.mapper.QuestionMapper;
import top.fyl.springboot.database.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:40
 * @description
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getAllQuestions() {
        return questionMapper.findAll();
    }

    @Override
    public List<Question> searchQuestionsByName(String name) {
        // 直接返回 List<Question>
        return questionMapper.findByName(name);
    }


    @Override
    public void addQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        List<Question> questions = questionMapper.findAllWithAnswers();

        // 确保所有问题的 answers 字段不为 null
        for (Question question : questions) {
            if (question.getAnswers() == null) {
                question.setAnswers(new ArrayList<>());
            }
        }
        return questions;
    }
}

