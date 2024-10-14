package top.fyl.springboot.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午4:36
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Special {
    private String id;
    private String title;
    private String introduction;
    private int viewCount;
    private int followersCount;
    private boolean isFollowing;
    private String banner;
    private long updated;

    // 与标签表的关联，一对多关系
    private List<Section> sections;
}