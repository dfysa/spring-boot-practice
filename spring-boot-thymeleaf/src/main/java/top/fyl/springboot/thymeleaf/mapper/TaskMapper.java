package top.fyl.springboot.thymeleaf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import top.fyl.springboot.thymeleaf.model.Task;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author dfysa
 * @data 12/9/2024 下午6:16
 * @description
 */

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
//    // 查询任务列表，支持分页和排序
//    IPage<Task> selectTasksPage(Page<?> page, @Param("sort") String sort);
}