package top.fyl.springboot.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author dfysa
 * @data 2024/10/28 下午2:44
 * @description
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(scheduleTaskExecutor());

    }

    @Bean
    public Executor scheduleTaskExecutor() {
        // 指定线程池大小为3
        return Executors.newScheduledThreadPool(3);


    }




}
