package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对应菜品的
 * Created by xuguoyuan on 2018/7/10.
 */
@ApiModel
public class OrderFoodInfo {
    @ApiModelProperty(hidden = true)
    /**
     * 主键
     */
    private String id;
    /**
     * 食物名称
     */
    private String foodName;
    /**
     * 食物价格
     */
    private Double price;
    /**
     * 折扣价格
     */
    private String discountPrice;
    /**
     * 是否打折
     */
    private String isDiscount;
    /**
     * 食物类型
     */
    private String foodType;
    /**
     * 食物图片
     */
    private String picture;
    /**
     * 食物信息备注
     */
    private String remarks;
    @ApiModelProperty(hidden = true)
    /**
     * 创建时间
     */
    private String addTime;
    /**
     * 食物状态 0 上架 1 下架
     */
    private String status;
    /**
     * 子照片
     */
    private String pictureSon1;
    /**
     * 子照片
     */
    private String pictureSon2;
    /**
     * 子照片
     */
    private String pictureSon3;
    /**
     * 商家id
     */
    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getPrice() {return price;}

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount;
    }

    public String getFoodType(){
        return foodType;
    }

    public void setFoodType(String foodType) { this.foodType=foodType;  }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status=status; }

    public String getPictureSon1() {
        return pictureSon1;
    }

    public void setPictureSon1(String pictureSon1) {
        this.pictureSon1 = pictureSon1;
    }

    public String getPictureSon2() {
        return pictureSon2;
    }

    public void setPictureSon2(String pictureSon2) {
        this.pictureSon2 = pictureSon2;
    }

    public String getPictureSon3() {
        return pictureSon3;
    }

    public void setPictureSon3(String pictureSon3) {
        this.pictureSon3 = pictureSon3;
    }


    @Override
    public String toString() {
        return "OrderFoodInfo{" +
                "id='" + id + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", discountPrice='" + discountPrice + '\'' +
                ", isDiscount='" + isDiscount + '\'' +
                ", foodType='" + foodType + '\'' +
                ", picture='" + picture + '\'' +
                ", remarks='" + remarks + '\'' +
                ", addTime='" + addTime + '\'' +
                ", status='" + status + '\'' +
                ", pictureSon1='" + pictureSon1 + '\'' +
                ", pictureSon2='" + pictureSon2 + '\'' +
                ", pictureSon3='" + pictureSon3 + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
