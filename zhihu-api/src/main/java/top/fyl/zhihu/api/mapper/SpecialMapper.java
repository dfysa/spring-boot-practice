package top.fyl.zhihu.api.mapper;

import top.fyl.zhihu.api.entity.Special;

import java.util.List;

/**
 * @author dfysa
 * @data 14/9/2024 下午2:11
 * @description
 */
public interface SpecialMapper {
    List<Special> selectAll();
    List<Special> selectByPage(int limit,int offset);
}
