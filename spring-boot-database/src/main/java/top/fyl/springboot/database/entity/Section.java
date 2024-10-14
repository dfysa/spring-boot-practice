package top.fyl.springboot.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dfysa
 * @data 2024/10/12 下午4:37
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {
    private String id;
    private String sectionTitle;
    private String specialId;
}