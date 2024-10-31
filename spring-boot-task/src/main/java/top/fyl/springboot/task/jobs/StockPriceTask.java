package top.fyl.springboot.task.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.fyl.springboot.task.entity.StockPrice;
import top.fyl.springboot.task.mapper.StockPriceMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author dfysa
 * @data 2024/10/28 下午2:58
 * @description 定时任务： 每隔10秒更新一次股票价格，并持久化到数据库
 */
//@Component
@AllArgsConstructor
@Slf4j
public class StockPriceTask {
    private final StockPriceMapper stockPriceMapper;
    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void updateStockPrice() {
        double rawPrice = 100 + random.nextDouble() * 50;

        // 保留两位小数
        BigDecimal price = BigDecimal.valueOf(rawPrice).setScale(2, RoundingMode.HALF_UP);

        StockPrice stockPrice = new StockPrice();
        stockPrice.setPrice(price.doubleValue());
        stockPrice.setName("小米");
        stockPrice.setUpdateTime(LocalDateTime.now());

        // 插入数据库
        stockPriceMapper.insert(stockPrice);
        log.info("更新股票价格：{}, 时间：{}", price, LocalDateTime.now());
    }
}
