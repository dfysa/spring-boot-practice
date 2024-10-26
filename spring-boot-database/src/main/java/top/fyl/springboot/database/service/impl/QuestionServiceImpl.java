package top.fyl.springboot.database.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Answer;

import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.mapper.AnswerMapper;
import top.fyl.springboot.database.mapper.QuestionMapper;
import top.fyl.springboot.database.service.QuestionService;
import top.fyl.springboot.database.util.PaginationUtil;

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
    public void deleteQuestionById(long id, long userId) {
        questionMapper.deleteQuestionById(id, userId);
    }

    @Override
    public PaginationUtil<Question> getAllQuestionsWithAnswers(Page page, String title) {
        List<Question> questions = questionMapper.findAllWithAnswers(page.getStartPosition(), page.getPageSize(), title);
        long total = questionMapper.countQuestions();

        questions.forEach(q -> {
            if (q.getAnswers() == null) {
                q.setAnswers(CollUtil.newArrayList());
            }
        });

        return new PaginationUtil<>(questions, total, page.getPageSize(), page.getPageNumber());
    }


    @Override
    public PaginationUtil<Question> getAllQuestionsWithAnswers(Page page) {
        List<Question> questions = questionMapper.findAllOnlyWithAnswers(page.getStartPosition(), page.getPageSize());
        long total = questionMapper.countQuestions();

        questions.forEach(q -> {
            if (q.getAnswers() == null) {
                q.setAnswers(CollUtil.newArrayList());
            }
        });

        return new PaginationUtil<>(questions, total, page.getPageSize(), page.getPageNumber());
    }


    @Override
    public Question getQuestionById(int id) {
        Question question = questionMapper.findQuestionById(id);
        // 确保 answers 不为 null
        if (question != null && question.getAnswers() == null) {
            question.setAnswers(new ArrayList<>());
        }
        return question;
    }

    @Override
    public void updateQuestionById(Question question) {
        questionMapper.updateQuestionById(question);
    }
}

