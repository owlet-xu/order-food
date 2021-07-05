package com.orderfood.springboot.seed.bz1.contract.model;

import java.util.List;

/**
 * 食物信息分页查询
 * Created by yangxuechao on 2018/7/11.
 */
public class OrderPageRepose {
    private int pageNum;
    private int pageSize;
    private int total;
    private List<OrderFoodInfo> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderFoodInfo> getContent() {
        return content;
    }

    public void setContent(List<OrderFoodInfo> content) {

        this.content = content;
    }
}
