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
    List<Question> findAllWithAnswers(@Param("offset") int offset, @Param("size") int size, @Param("title") String title);
    List<Question> findAllOnlyWithAnswers(@Param("offset") int offset, @Param("size") int size);


    long countQuestions();  // 查询问题总数
    Question findQuestionById(int id);

    // 根据问题 ID 更新问题内容的方法声明
    void updateQuestionById(Question question);

    List<Question> findQuestionsByUserId(@Param("userId") int userId);

    // 新增逻辑删除问题
    void deleteQuestionById(@Param("id") long id, @Param("userId") long userId);

}

