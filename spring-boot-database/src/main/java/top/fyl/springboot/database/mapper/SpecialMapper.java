package top.fyl.springboot.database.mapper;

import top.fyl.springboot.database.entity.Special;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/12 下午4:37
 * @description
 */

public interface SpecialMapper {
    List<Special> findAll();
}
