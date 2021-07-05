package com.orderfood.springboot.seed.bz1.service.repository;

import com.orderfood.springboot.seed.bz1.service.entity.OrderMeanListEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/9.
 */
public interface OrderMeanlistRepository extends CrudRepository<OrderMeanListEntity,String> {
    /**
     * 根据订单id查询订单菜品
     * @param orderId
     * @return
     */
    List<OrderMeanListEntity> findByOrderId(String orderId);

    /**
     * 根据菜品id查询订单菜品表
     * @param orderId
     * @return
     */
    List<OrderMeanListEntity> findByFoodId(String orderId);

}
