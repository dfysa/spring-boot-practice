package top.fyl.springboot.database.service;

import top.fyl.springboot.database.entity.User;

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
}
