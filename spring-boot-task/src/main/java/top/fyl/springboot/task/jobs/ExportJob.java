package top.fyl.springboot.task.jobs;

import com.alibaba.excel.EasyExcel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import top.fyl.springboot.task.entity.Student;
import top.fyl.springboot.task.mapper.StudentMapper;

import javax.cache.annotation.CacheRemoveAll;
import java.util.List;
import java.util.UUID;

/**
 * @author dfysa
 * @data 2024/10/28 下午4:25
 * @description
 */
@Slf4j
@AllArgsConstructor
public class ExportJob extends QuartzJobBean {
    protected  final StudentMapper studentMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("");
        List<Student> students =studentMapper.selectList(null);
        String fileName="D:\\"+ UUID.randomUUID()+".xlsx";
        EasyExcel.write(fileName,Student.class)
                .sheet("学生数据")
                .doWrite(()-> students
        );
    }
}
