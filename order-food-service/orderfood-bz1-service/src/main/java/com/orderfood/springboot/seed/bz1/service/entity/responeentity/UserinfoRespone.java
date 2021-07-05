package com.orderfood.springboot.seed.bz1.service.entity.responeentity;

import com.orderfood.springboot.seed.bz1.contract.model.LoginInfo;
import com.orderfood.springboot.seed.bz1.contract.model.OrderUserInfo;

/**
 * Created by yangxuechao on 2018/7/3.
 */
public class UserinfoRespone {
    private Boolean status;
    private LoginInfo loginInfo;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public UserinfoRespone(LoginInfo loginInfo,Boolean status){
        this.loginInfo=loginInfo;
        this.status=status;
    }
    @Override
    public String toString() {
        return "UserinfoRespone{" +
                "status=" + status +
                ", loginInfo=" + loginInfo +
                '}';
    }
}
