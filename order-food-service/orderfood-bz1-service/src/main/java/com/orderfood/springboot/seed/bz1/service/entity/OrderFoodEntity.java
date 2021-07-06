package com.orderfood.springboot.seed.bz1.service.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by xuguoyuan on 2018/7/10.
 */
@Entity
@Table(name = "os_food", schema= "order_food")
public class OrderFoodEntity {
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue( generator = "idGenerator")
    @Column(name = "id")
    private String id;
    private String foodName;
    private Double price;
    private String discountPrice;
    private String isDiscount;
    private String foodType;
    private String picture;
    private String remarks;
    private String status;
    private String addTime;
    private String pictureSon1;
    private String pictureSon2;
    private String pictureSon3;
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

    public Double getPrice() {
        return price;
    }

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

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

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
        return "OrderFoodEntity{" +
                "id='" + id + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", discountPrice='" + discountPrice + '\'' +
                ", isDiscount='" + isDiscount + '\'' +
                ", foodType='" + foodType + '\'' +
                ", picture='" + picture + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                ", addTime='" + addTime + '\'' +
                ", pictureSon1='" + pictureSon1 + '\'' +
                ", pictureSon2='" + pictureSon2 + '\'' +
                ", pictureSon3='" + pictureSon3 + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
