package top.fyl.springboot.configure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dfysa
 * @data 14/9/2024 下午2:08
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Special {
    private String  id;
    private String  title;
    private String  banner;
    private String  introduction;
    private String isFollowing;
    private Integer followersCount;
    private Integer viewCount;
    private String updated;
}
