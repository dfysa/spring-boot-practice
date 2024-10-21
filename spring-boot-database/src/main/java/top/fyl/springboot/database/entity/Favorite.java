package top.fyl.springboot.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dfysa
 * @data 2024/10/16 下午9:24
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "favorites")
public class Favorite {
    private Integer favoriteId;  // 主键
    private int userId;          // 用户ID
    private int questionId;      // 问题ID
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private boolean deleteFlag;  // 是否删除标志
}