package top.fyl.zhihu.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.config.Task;
import top.fyl.zhihu.api.entity.Special;

import java.util.List;

/**
 * @author dfysa
 * @data 14/9/2024 下午2:19
 * @description
 */
public interface SpecialService   {
    List<Special> getAll();
    List<Special> getByPage(int limit,int offset);
}
