package top.fyl.springboot.redis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author dfysa
 * @data 2024/10/21 下午4:12
 * @description
 */
@Getter
@AllArgsConstructor
public enum AccountStatusEnum {
    /**
     * 停⽤
     */
    DISABLE(0, "停⽤"),
    /**
     * 正常
     */
    ENABLED(1, "正常");
    private final int value;
    private final String name;
    public static String getNameByValue(int value) {
        for (AccountStatusEnum s : AccountStatusEnum.values()) {
            if (s.getValue() == value) {
                return s.getName();
            }
        }
        return "";
    }
    public static Integer getValueByName(String name) {
        for (AccountStatusEnum s : AccountStatusEnum.values()) {
            if (Objects.equals(s.getName(), name)) {
                return s.getValue();
            }
        }
        return null;
    }
}