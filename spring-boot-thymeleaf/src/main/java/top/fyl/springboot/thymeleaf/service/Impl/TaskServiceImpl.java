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

}