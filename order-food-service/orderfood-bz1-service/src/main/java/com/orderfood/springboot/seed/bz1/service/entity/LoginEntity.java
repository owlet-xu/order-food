package com.orderfood.springboot.seed.bz1.service.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by xuguoyuan on 2018/7/5.
 */
@Entity
@Table(name = "os_user", schema= "order_food")
public class LoginEntity {
    @Id

    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue( generator = "idGenerator")

    @Column(name = "id")
    private String id;

    private String name;

    private String passWord;

    private String phone;

    private String createTime;

    private String address;

    private String email;

    private String type;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "LoginEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
