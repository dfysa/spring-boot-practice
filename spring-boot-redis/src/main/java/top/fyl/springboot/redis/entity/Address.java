package top.fyl.springboot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author dfysa
 * @data 2024/10/21 下午2:05
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = -1404424494810978336L;
            private String province;
    private String  city;

}
