package top.fyl.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.quickstart.entity.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:42
 * @description
 */
@Service
public class UserService {
    private final List<User> users = List.of(
            new User(1L, " 张三", 22),
            new User(2L, "张三丰", 17),
            new User(3L, "张 三疯", 19),
            new User(4L, "张三⻛ ", 16),
            new User(5L, "张三峰 ", 25),
            new User(5L, "明若成", 25)
    );

    public List<String> getAdultUsers(String initial) {
        return users.stream()
                .filter(user -> user.getAge() > 18)
                .map(User::getName)
                .map(String::trim) // 去除空格
                .filter(name -> initial == null || name.startsWith(initial))
                .collect(Collectors.toList());
    }
}