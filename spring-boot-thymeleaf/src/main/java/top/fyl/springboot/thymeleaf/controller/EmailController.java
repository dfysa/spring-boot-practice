package top.fyl.springboot.thymeleaf.controller;

/**
 * @author dfysa
 * @data 2024/9/9 下午4:05
 * @description
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.fyl.springboot.thymeleaf.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/9 下午3:50
 * @description
 */
@Controller
public class EmailController {
    private List<Email> emails = new ArrayList<>();

    @GetMapping("/emailList")
    public String emailList(Model model) {
        model.addAttribute("emails", emails);
        return "emailList";
    }

    @PostMapping("/addEmail")
    public String addEmail(@RequestParam String name, @RequestParam String email) {
        Long id = (long) (emails.size() + 1);
        Email newEmail = new Email(id, name, email);
        emails.add(newEmail);
        return "redirect:/emailList";
    }
}
