package top.fyl.springboot.database.service;

import top.fyl.springboot.database.entity.Answer;
import top.fyl.springboot.database.entity.Question;
import top.fyl.springboot.database.entity.User;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午10:35
 * @description
 */
public interface UserService {
    User login(String username, String password);
    void register(User user);
    String generateToken(User user);
    // 新增方法声明
    User getUserById(int userId);

    List<Question> getQuestionsByUserId(int userId);
    List<Answer> getAnswersByUserId(int userId);



//    boolean verifyCode(String email, String code);
//    void sendVerificationCode(String email);
}
