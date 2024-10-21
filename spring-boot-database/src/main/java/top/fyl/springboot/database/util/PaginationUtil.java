package top.fyl.springboot.database.util;

import lombok.Data;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/20 下午7:29
 * @description
 */
@Data
public class PaginationUtil<T> {
    private List<T> records;  // 当前页的数据
    private long total;        // 总记录数
    private int pageSize;      // 每页大小
    private int currentPage;   // 当前页码

    public PaginationUtil(List<T> records, long total, int pageSize, int currentPage) {
        this.records = records;
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
}