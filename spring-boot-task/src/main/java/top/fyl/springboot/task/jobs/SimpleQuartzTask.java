package top.fyl.springboot.task.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/10/28 下午4:00
 * @description
 */
@Slf4j
public class SimpleQuartzTask extends QuartzJobBean {

    // context 可获得任务的上下文信息
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("SimpleQuartzTask,执行时间：{}", LocalDateTime.now());
    }
}
