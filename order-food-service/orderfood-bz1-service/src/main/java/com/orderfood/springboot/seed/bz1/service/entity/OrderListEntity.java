package com.orderfood.springboot.seed.bz1.service.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by xuguoyuan on 2018/7/9.
 */
@Entity
@Table(name = "os_orderlist", schema= "order_food")

public class OrderListEntity {
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue( generator = "idGenerator")
    private String id;

    private String orderNu;

    private String orderId;

    private String orderName;

    private String orderPhone;

    private String orderAddress;

    private String status;

    private Double totalPrice;

    private String sellerId;
    private String sellerName;
    private String sellerPhone;
    private String createTime;

    private String sellerRemark;

    private String orderRemark;

    private Double count;

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNu() {
        return orderNu;
    }

    public void setOrderNu(String orderNu) {
        this.orderNu = orderNu;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderListEntity{" +
                "id='" + id + '\'' +
                ", orderNu='" + orderNu + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", status='" + status + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", sellerRemark='" + sellerRemark + '\'' +
                ", orderRemark='" + orderRemark + '\'' +
                ", count=" + count +
                '}';
    }
}
