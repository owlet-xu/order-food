package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by xuguoyuan on 2018/7/6.
 */
@ApiModel
public class IsLoginInfo {
    @ApiModelProperty(hidden = true)
    /**
     * 主键
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String id;
    /**
     *电话号码
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String phone;
    /**
     * 密码
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String passWord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "IsLoginInfo{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
