package com.orderfood.springboot.seed.bz1.contract.service;

import com.orderfood.springboot.seed.bz1.contract.model.*;

import java.util.List;

/**
 * Created by xuguoyuan on 2018/7/5.
 */
public interface OrderUserService {

    /**
     * 根据条件查询用户信息
     * @param orderUserPageRequest
     * @return
     */
    OrderUserPageRepose selectlist(OrderUserPageRequest orderUserPageRequest);

    /**
     * 更改用户信息
     * @param orderUserInfo
     * @return
     */
    Boolean update(OrderUserInfo orderUserInfo);

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    List<OrderUserInfo> findByPhone(String phone);

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    List<OrderUserInfo> findByEmail(String email );

    /**
     * 注册成功保存注册信息
     * @param loginInfo
     * @return
     */
    OrderUserInfo save(LoginInfo loginInfo);

    /**id
     * 根据用户id查询用户信息
     * @param
     * @return
     */
    OrderUserInfo findById(String id);

}
