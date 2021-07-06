package com.orderfood.springboot.seed.bz1.contract.model;

import java.io.Serializable;
import java.util.List;

/**
 * 食物信息分页查询，根据下面的一些条件
 * Created by xuguoyuan on 2018/7/11.
 */
public class OrderPageRequest implements Serializable  {
    private Integer PaSize;
    private Integer PageNum;
    private String foodName;
    private Double minPrice;
    private Double maxPrice;
    private String foodType;
    private String status;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getPaSize() {
        return PaSize;
    }

    public void setPaSize(Integer paSize) {
        PaSize = paSize;
    }

    public Integer getPageNum() {
        return PageNum;
    }

    public void setPageNum(Integer pageNum) {
        PageNum = pageNum;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        if("0".equals(foodType)){
            this.foodType = "热干面";
        }
        this.foodType = foodType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
