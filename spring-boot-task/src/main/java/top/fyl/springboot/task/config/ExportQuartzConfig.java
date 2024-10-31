package top.fyl.springboot.task.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fyl.springboot.task.jobs.ExportJob;
import top.fyl.springboot.task.jobs.SimpleQuartzTask;

/**
 * @author dfysa
 * @data 2024/10/28 下午4:02
 * @description
 */
//@Configuration
public class  ExportQuartzConfig {
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(ExportJob.class)
                .withIdentity("exportTask")
                .storeDurably()

                .build();
    }


    @Bean
    public Trigger trigger(){
        // 指定定时任务的执行
        CronScheduleBuilder scheduleBuilder =CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        // 返回任务触发器
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("exportTask")
                .withSchedule(scheduleBuilder)
                .build();

    }
}
