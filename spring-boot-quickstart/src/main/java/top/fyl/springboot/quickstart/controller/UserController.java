package top.fyl.springboot.quickstart.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.quickstart.entity.Meeting;
import top.fyl.springboot.quickstart.service.MeetingService;
import top.fyl.springboot.quickstart.service.UserService;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:33
 * @description
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adults")
    public List<String> getAdultsUserName(
            @RequestParam(value = "initial", required = false) String initial) {
        return userService.getAdultUsers(initial);
    }
}
