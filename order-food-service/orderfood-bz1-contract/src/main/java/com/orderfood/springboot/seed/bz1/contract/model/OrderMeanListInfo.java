package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by xuguoyuan on 2018/7/9.
 */
@ApiModel
public class OrderMeanListInfo {
    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 食物id
     */
    private String foodId;
    /**
     * 食物名字
     */
    private String foodName;
    /**
     * 食物价格
     */
    private Double price;
    /**
     * 订单id
     */
    private String userId;
    /**
     * 商家id
     */
    private String shopId;
    /**
     * 点单分数
     */
    private Double count;
    /**
     * 食物类型
     */
    private String type;
    /**
     * 添加时间
     */
    @ApiModelProperty(hidden = true)
    private String addTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderMeanListInfo{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", userId='" + userId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", count=" + count +
                ", type='" + type + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
