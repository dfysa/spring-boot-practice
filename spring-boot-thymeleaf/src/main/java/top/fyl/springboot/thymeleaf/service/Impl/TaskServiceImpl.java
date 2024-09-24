package top.fyl.springboot.thymeleaf.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fyl.springboot.thymeleaf.mapper.TaskMapper;
import top.fyl.springboot.thymeleaf.model.Task;
import top.fyl.springboot.thymeleaf.service.TaskService;

import java.util.List;

/**
 * @author dfysa
 * @data 12/9/2024 下午8:05
 * @description
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    // 继承 MyBatis Plus 提供的通用 Service 实现类

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public IPage<Task> getTasksPage(int pageNum, int pageSize, String sort) {
        Page<Task> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();

        // 先按 completed 字段排序，0 的记录在前，1 的记录在后
        queryWrapper.orderByAsc("completed");

        // 按照其他排序条件（如 priority）
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(" ");
            if (sortParams.length == 2) {
                queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(sortParams[1]), sortParams[0]);
            }
        }

        System.out.println("Executing query with page number: " + pageNum + " and page size: " + pageSize);
        System.out.println("Sort query: " + queryWrapper.getSqlSegment());

        IPage<Task> resultPage = taskMapper.selectPage(page, queryWrapper);
        System.out.println("Total records: " + resultPage.getTotal());
        System.out.println("Total pages: " + resultPage.getPages());

        return resultPage;
    }
    @Override
    public List<Task> getTasksWithNearestDeadline() {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();

        // 查询条件：未完成的任务（completed = 0），并按截止日期排序
        queryWrapper.eq("completed", 0)
                .orderByAsc("deadline")  // 按截止日期从近到远排序
                .last("LIMIT 3");  // 最多获取三条任务

        return taskMapper.selectList(queryWrapper);
    }

}