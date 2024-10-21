package top.fyl.springboot.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
/**
 * @author dfysa
 * @data 2024/10/12 下午10:33
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    private int answerId;
    // 原 question_id
    private int relatedQuestionId;
    private Integer parentId;
    // 回答用户 ID
    private int userId;
    // 回答发布人用户名（查询时动态获取）
    private String userName;
    private String accountname;
    private String answerContent;
    private String avatar; // 问题发布人头像
    @Column(name = "create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date acreateTime;
    @Column(name = "update_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date aupdateTime;

    private boolean deleteFlag;

    // 子回复
    private List<Answer> replies;

    private int answerLikeCount;  // 回答的点赞数
}


