package com.zhuxi.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> items;   //  当前页数


    // 传统分页字段
/*
    private int currentPage; //  当前页码
    private int pageSize;  //   每页数据条数
    private int totalPages; //   总页数
    private long totalItems; //   总数据条数
*/

    // 游标分页字段
    private Long nextCursor; // 下一页游标
    private boolean hasPrevious; //  是否有上一页
     private boolean hasNext; //  是否有下一页



/*
    public PageResult(List<T> items, long totalItems, int pageNum, int pageSize) {
        this.items = items;
        this.totalItems = totalItems;
        this.currentPage = pageNum;
        this.pageSize = pageSize;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
    }
*/

    /**
     * 游标分页构造器
     * @param items 当前页数据
     * @param nextCursor 下一页的游标
     */
    public PageResult(List<T> items, Long nextCursor, boolean hasPrevious, boolean hasNext) {
        this.items = items;
        this.nextCursor = nextCursor;
         this.hasNext = hasNext;
        this.hasPrevious =  hasPrevious;
    }
}
