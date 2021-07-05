package com.orderfood.springboot.seed.bz1.contract.model;

import java.io.Serializable;

public class OrderListPageRequest implements Serializable {
    private Integer PaSize;
    private Integer PageNum;
    private String num;
    private String name;
    private String address;
    private String status;
    private String beginTime;
    private String endTime;
    private String userId;
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "OrderListPageRequest{" +
                "PaSize=" + PaSize +
                ", PageNum=" + PageNum +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
