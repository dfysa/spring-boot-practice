package top.fyl.springboot.thymeleaf.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.fyl.springboot.thymeleaf.mapper.TaskMapper;
import top.fyl.springboot.thymeleaf.model.Email;
import top.fyl.springboot.thymeleaf.model.Task;
import top.fyl.springboot.thymeleaf.service.TaskService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/9 下午2:55
 * @description
 */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskService taskService;

    @GetMapping("/taskList")
    public String taskList(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        int pageSize = 3; // 每页显示的任务数量
        String sort = "priority DESC"; // 按优先级从高到低排序

        IPage<Task> taskPage = taskService.getTasksPage(pageNum, pageSize, sort);

        // 调试输出
        System.out.println("Current Page: " + pageNum);
        System.out.println("Total Records: " + taskPage.getTotal());
        System.out.println("Total Pages: " + taskPage.getPages());
        System.out.println("Tasks: " + taskPage.getRecords());

        model.addAttribute("tasks", taskPage.getRecords());
        model.addAttribute("currentPage", pageNum); // 当前页码
        model.addAttribute("totalPages", taskPage.getPages()); // 总页数

        return "taskList"; // 返回 Thymeleaf 模板名称
    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam String description, @RequestParam int priority, @RequestParam String deadline) {
        Task newTask = new Task();
        newTask.setDescription(description);
        newTask.setPriority(priority);
        newTask.setDeadline(LocalDate.parse(deadline));
        newTask.setCompleted(false);
        taskMapper.insert(newTask); // 使用 MyBatis-Plus 的 insert 方法
        return "redirect:/taskList";
    }

    @PostMapping("/updateTask")
    public String updateTask(@RequestParam Long id) {
        Task task = taskMapper.selectById(id); // 使用 MyBatis-Plus 的 selectById 方法
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            taskMapper.updateById(task); // 使用 MyBatis-Plus 的 updateById 方法
        }
        return "redirect:/taskList";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id) {
        taskMapper.deleteById(id); // 使用 MyBatis-Plus 的 deleteById 方法
        return "redirect:/taskList";
    }
}