package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by xuguoyuan on 2018/7/9.
 */
@ApiModel
public class OrderListInfo {
    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 订单号
     */
    private String orderNu;
    /**
     * 下单人id
     */
    private String orderId;
    /**
     * 下单人姓名
     */
    private String orderName;
    /**
     * 下单人电话
     */
    private String orderPhone;
    /**
     * 下单人地址
     */
    private String orderAddress;
    /**
     * 订单状态 0 取消 1 等待接单 2 接单 3 用户取走
     */
    private String status;
    /**
     * 总共的价格
     */
    private String totalPrice;
    /**
     * 商家id
     */
    private String sellerId;
    /**
     * 商家姓名
     */
    private String sellerName;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private String createTime;
    /**
     * 卖家电话
     */
    private String sellerPhone;
    /**
     * 卖家留言
     */
    private String sellerRemark;
    /**
     * 下单留言
     */
    private String orderRemark;
    /**
     * 菜品数量
     */
    private Double count;
    /**
     * 订单子菜品信息
     */
    private List<OrderMeanListInfo> orderMeanListInfoList;

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

    public List<OrderMeanListInfo> getOrderMeanListInfoList() {
        return orderMeanListInfoList;
    }

    public void setOrderMeanListInfoList(List<OrderMeanListInfo> orderMeanListInfoList) {
        this.orderMeanListInfoList = orderMeanListInfoList;
    }

    @Override
    public String toString() {
        return "OrderListInfo{" +
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
                ", orderMeanListInfoList=" + orderMeanListInfoList +
                '}';
    }
}
