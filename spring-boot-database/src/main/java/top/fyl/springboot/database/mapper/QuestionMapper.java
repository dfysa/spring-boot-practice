package top.fyl.springboot.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.fyl.springboot.database.entity.Question;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:34
 * @description
 */
@Mapper
public interface QuestionMapper {
    List<Question> findAll();

    List<Question> findByName(@Param("name") String name);  // 返回类型必须是 List<Question>

    void insertQuestion(Question question);
    List<Question> findAllWithAnswers();
}

