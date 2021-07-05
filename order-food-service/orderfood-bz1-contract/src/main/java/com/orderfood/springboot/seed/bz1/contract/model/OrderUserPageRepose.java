package com.orderfood.springboot.seed.bz1.contract.model;

import java.util.List;

/**
 * 用户信息返回对象
 * Created by yangxuechao on 2018/7/12.
 */
public class OrderUserPageRepose {
    private int pageNum;
    private int pageSize;
    private Long total;
    /**
     * 返回的个人信息
     */
    private List<OrderUserInfo> content;

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

    public List<OrderUserInfo> getContent() {
        return content;
    }

    public void setContent(List<OrderUserInfo> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "OrderUserPageRepose{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", content=" + content +
                '}';
    }
}
