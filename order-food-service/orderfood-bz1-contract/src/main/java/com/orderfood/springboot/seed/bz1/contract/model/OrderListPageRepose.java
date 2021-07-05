package com.orderfood.springboot.seed.bz1.contract.model;

import java.util.List;

public class OrderListPageRepose {
    private int pageNum;
    private int pageSize;
    private Long total;
    private List<OrderListInfo> content;

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<OrderListInfo> getContent() {
        return content;
    }

    public void setContent(List<OrderListInfo> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "OrderListPageRepose{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", content=" + content +
                '}';
    }

}
