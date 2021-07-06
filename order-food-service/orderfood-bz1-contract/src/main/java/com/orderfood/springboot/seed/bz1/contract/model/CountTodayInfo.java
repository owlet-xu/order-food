package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;

import java.util.Map;

/**
 * Created by xuguoyuan on 2018/7/18.
 */
@ApiModel
public class CountTodayInfo {
    /**
     * 存放金额
     */
    private Map<String,Double> countMoneys;
    /**
     * 存放数量
     */
    private  Map<String,Integer> countNumber;

    public Map<String, Double> getCountMoneys() {
        return countMoneys;
    }

    public void setCountMoneys(Map<String, Double> countMoneys) {
        this.countMoneys = countMoneys;
    }

    public Map<String, Integer> getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Map<String, Integer> countNumber) {
        this.countNumber = countNumber;
    }

    @Override
    public String toString() {
        return "CountTodayInfo{" +
                "countMoneys=" + countMoneys +
                ", countNumber=" + countNumber +
                '}';
    }
}
