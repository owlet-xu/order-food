package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/9.
 */
@ApiModel
public class OrderDetailList {
    @ApiModelProperty(hidden = true)
    private String id;
    private String orderNu;
    private String orderId;
    private String orderName;
    private String orderPhone;
    private String orderAddress;
    private String status;
    private String totalPrice;
    private String sellerId;
    private String sellerName;
    private String createTime;
    private String sellerPhone;
    private String sellerRemark;
    private String orderRemark;
    private Double count;
    private boolean expand;

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
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

    private List<OrderMeanListInfo> orderDetail;

    public List<OrderMeanListInfo> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderMeanListInfo> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getId() {
        return id;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }


    @Override
    public String toString() {
        return "OrderDetailList{" +
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
                ", createTime='" + createTime + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", sellerRemark='" + sellerRemark + '\'' +
                ", orderRemark='" + orderRemark + '\'' +
                ", count=" + count +
                ", expand=" + expand +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
