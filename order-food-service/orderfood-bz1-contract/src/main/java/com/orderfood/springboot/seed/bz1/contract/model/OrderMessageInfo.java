package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Created by xuguoyuan on 2018/7/17.
 */
@ApiModel
public class OrderMessageInfo {
    private int countNumber;
    private List<OrderListInfo> listInfoList;

    public int getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(int countNumber) {
        this.countNumber = countNumber;
    }

    public List<OrderListInfo> getListInfoList() {
        return listInfoList;
    }

    public void setListInfoList(List<OrderListInfo> listInfoList) {
        this.listInfoList = listInfoList;
    }

    @Override
    public String toString() {
        return "OrderMessageInfo{" +
                "countNumber=" + countNumber +
                ", listInfoList=" + listInfoList +
                '}';
    }
}
