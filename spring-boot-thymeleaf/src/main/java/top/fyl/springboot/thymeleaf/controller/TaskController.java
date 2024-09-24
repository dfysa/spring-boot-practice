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
        int pageSize = 3;
        String sort = "priority DESC";

        IPage<Task> taskPage = taskService.getTasksPage(pageNum, pageSize, sort);

        // 获取最多三条截止日期最近且未完成的任务
        List<Task> nearestDeadlineTasks = taskService.getTasksWithNearestDeadline();

        model.addAttribute("tasks", taskPage.getRecords());
        model.addAttribute("nearestDeadlineTasks", nearestDeadlineTasks);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", taskPage.getPages());

        return "taskList";
    }
    @PostMapping("/addTask")
    public String addTask(@RequestParam String description, @RequestParam int priority, @RequestParam String deadline) {
        Task newTask = new Task();
        newTask.setDescription(description);
        newTask.setPriority(priority);
        newTask.setDeadline(LocalDate.parse(deadline));
        newTask.setCompleted(false);
        // 使用 MyBatis-Plus 的 insert 方法
        taskMapper.insert(newTask);
        return "redirect:/taskList";
    }

    @PostMapping("/updateTask")
    public String updateTask(@RequestParam Long id) {
        // 使用 MyBatis-Plus 的 selectById 方法
        Task task = taskMapper.selectById(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            // 使用 MyBatis-Plus 的 updateById 方法
            taskMapper.updateById(task);
        }
        return "redirect:/taskList";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id) {
        // 使用 MyBatis-Plus 的 deleteById 方法
        taskMapper.deleteById(id);
        return "redirect:/taskList";
    }
}