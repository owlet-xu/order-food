package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

/**
 * Created by yangxuechao on 2018/7/17.
 */
@ApiModel
public class OrderSellerInfo {
    @ApiModelProperty(hidden = true)
    private String id;
    private String orderId;
    private String status;
    private String sellerRemark;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark;
    }

    @Override
    public String toString() {
        return "OrderSellerInfo{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", sellerRemark='" + sellerRemark + '\'' +
                '}';
    }
}
