package top.fyl.springboot.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/10/28 下午2:55
 * @description
 */
@Data
@TableName("stock_price")
public class StockPrice {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime updateTime;
}
