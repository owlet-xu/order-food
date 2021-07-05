package com.orderfood.springboot.seed.bz1.contract.model;

import java.io.Serializable;

/**
 * 个人信息查询的分页
 * Created by yangxuechao on 2018/7/12.
 */
public class OrderUserPageRequest implements Serializable {
    /**
     * 姓名
     */
    private String name;
    /**
     *电话号码
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;

    private Integer PaSize;
    private Integer PageNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        return "OrderUserPageRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", PaSize=" + PaSize +
                ", PageNum=" + PageNum +
                '}';
    }
}
