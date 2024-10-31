package top.fyl.springboot.task.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dfysa
 * @data 2024/10/28 下午2:35
 * @description
 */
//@Component
@Slf4j
public class ScheduledJobs {
//    @Scheduled(fixedRate = 3000)
//    public  void fiexedRateJob() throws InterruptedException{
//        log.info("fiexedRateJob start:{}",new Date());
//        Thread.sleep(5000);
//        log.info("fiexedRateJob end:{}",new Date());
//
//    }

//    @Scheduled(fixedDelay = 3000)
//    public  void fiexedDelayJob() throws InterruptedException{
//        log.info("fiexedDelayJob start:{}",new Date());
//        Thread.sleep(5000);
//        log.info("fiexedDelayJob end:{}",new Date());
//
//    }
//
@Scheduled(cron ="0/3 * * * * ?")
public  void fiexedDelayJob() throws InterruptedException{
    log.info("........cron 执行{}",new Date());
//    Thread.sleep(5000);
//    log.info("fiexedDelayJob end:{}",new Date());

}


}
