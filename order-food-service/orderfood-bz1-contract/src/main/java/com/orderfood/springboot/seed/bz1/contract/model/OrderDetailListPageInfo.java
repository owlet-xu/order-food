package com.orderfood.springboot.seed.bz1.contract.model;

import java.util.List;

public class OrderDetailListPageInfo {
    private int pageNum;
    private int pageSize;
    private Long total;
    private List<OrderDetailList> orderDetailListList;

    public List<OrderDetailList> getOrderDetailListList() {
        return orderDetailListList;
    }

    public void setOrderDetailListList(List<OrderDetailList> orderDetailListList) {
        this.orderDetailListList = orderDetailListList;
    }

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

    @Override
    public String toString() {
        return "OrderDetailListPageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", orderDetailListList=" + orderDetailListList +
                '}';
    }
}
