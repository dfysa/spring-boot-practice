package top.fyl.springboot.configure.controller;



import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.configure.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public String createUser(@Valid @RequestBody User user) {
        return "用户通过！";
    }
}