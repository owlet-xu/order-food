package com.orderfood.springboot.seed.bz1.service.entity.responeentity;

import com.orderfood.springboot.seed.bz1.contract.model.IsLoginInfo;

/**
 * Created by xuguoyuan on 2018/7/6.
 */
public class IsLoginRespone {
    private Boolean status;
    private IsLoginInfo isLoginInfo;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public IsLoginInfo getIsLoginInfo() {
        return isLoginInfo;
    }

    public void setIsLoginInfo(IsLoginInfo isLoginInfo) {
        this.isLoginInfo = isLoginInfo;
    }

    public IsLoginRespone(IsLoginInfo isLoginInfo,Boolean status){
        this.status=status;
        this.isLoginInfo=isLoginInfo;
    }

    @Override
    public String toString() {
        return "IsLoginRespone{" +
                "status=" + status +
                ", isLoginInfo=" + isLoginInfo +
                '}';
    }
}
