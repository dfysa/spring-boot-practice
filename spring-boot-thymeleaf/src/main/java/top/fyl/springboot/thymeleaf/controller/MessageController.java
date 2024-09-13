package top.fyl.springboot.thymeleaf.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dfysa
 * @data 2024/9/9 下午2:35
 * @description
 */
@Controller
public class MessageController {
    @GetMapping("/msg")
    public String getMsg(Model model){
        // 将“messsage” 属性传到视图
        model.addAttribute("message","Hello,Thymeleaf!");
        // 返回的视图名称，默认在 resources/templates/msg.html
        return "msg";

    }
}
