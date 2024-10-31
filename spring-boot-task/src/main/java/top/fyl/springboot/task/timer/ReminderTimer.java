package top.fyl.springboot.task.timer;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.Timer;
/**
 * @author dfysa
 * @data 2024/10/28 下午2:10
 * @description 定时发送提醒消息
 */
@Slf4j
public class ReminderTimer {
    public static void main(String[] args) {
        // 定时器
        Timer timer = new Timer();
        // 任务task
        TimerTask task =new TimerTask() {
            @Override
            public void run() {

                // 要做的事情
                log.info("请休息一下，喝口水吧");
            }
        };

        // 每隔五秒执行任务
        timer.schedule(task,0,5000);


    }
}
