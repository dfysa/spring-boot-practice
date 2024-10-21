package top.fyl.springboot.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/16 下午3:37
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "likes")
public class Like {
    private Integer likeId;           // 点赞 ID
    private int userId;               // 用户 ID
    private String userName;          // 用户名
    private String accountname;       // 账号名
    private String avatar;            // 头像
    private int targetId;             // 目标 ID
    private byte type;                // 类型（1：回答，2：问题）
    private Date lcreateTime;         // 创建时间
    private Date lupdateTime;         // 更新时间
    private boolean ldeleteFlag;      // 是否删除

    // 新增的字段
    private String questionTitle;     // 问题标题
    private String answerContent;     // 答案内容（仅当类型为回答时使用）
    private Integer questionId;       // 所属问题 ID（仅当类型为回答时使用）
}