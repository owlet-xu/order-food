package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;

/**
 * Created by yangxuechao on 2018/7/18.
 */
@ApiModel
public class OrderCountInfo {
    private String name;
    private int totalMoney;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "OrderCountInfo{" +
                "name='" + name + '\'' +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
