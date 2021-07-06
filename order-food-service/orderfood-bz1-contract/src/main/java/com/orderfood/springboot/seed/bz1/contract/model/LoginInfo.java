package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * 封装登陆的对象
 * Created by xuguoyuan on 2018/7/5.
 */
@ApiModel
public class LoginInfo {
    @ApiModelProperty(hidden = true)
    /**
     * 主键
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String id;
    /**
     * 姓名
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String name;
    /**
     * 密码
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String passWord;
    /**
     * 电话
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String phone;
    /**
     * 邮件
     */
    @Size(min=1, max =255)
    private String email;
    /**
     * 地址
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String address;
    /**
     * 创建时间,后台注入,不需要前台
     */
    @ApiModelProperty(hidden = true)
    private String careteTime;
    /**
     * 用户类型
     */
    private String type;

    public String getCareteTime() {
        return careteTime;
    }

    public void setCareteTime(String careteTime) {
        this.careteTime = careteTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", careteTime='" + careteTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
