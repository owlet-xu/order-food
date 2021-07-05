package com.orderfood.springboot.seed.bz1.service.repository;

import com.orderfood.springboot.seed.bz1.service.entity.OrderUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/5.
 */
public interface OrderUserRepository extends JpaRepository<OrderUserEntity,String>,JpaSpecificationExecutor<OrderUserEntity> {
    /**
     * 根据电话查询用户信息
     * @param phone
     * @return
     */
    List<OrderUserEntity> findByPhone(String phone);

    /**
     * 根据用户的邮箱查询用户信息
     * @param email
     * @return
     */
    List<OrderUserEntity> findByEmail(String email);


    /**
     *
     * @param LoginEntity
     * @return
     */
//    LoginEntity save(LoginEntity LoginEntity);

}
