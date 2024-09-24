package top.fyl.springboot.thymeleaf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.fyl.springboot.thymeleaf.model.Task;

import java.util.List;

/**
 * @author dfysa
 * @data 12/9/2024 下午8:05
 * @description
 */
public interface TaskService extends IService<Task> {
    IPage<Task> getTasksPage(int pageNum, int pageSize, String sort);
     List<Task> getTasksWithNearestDeadline();

}