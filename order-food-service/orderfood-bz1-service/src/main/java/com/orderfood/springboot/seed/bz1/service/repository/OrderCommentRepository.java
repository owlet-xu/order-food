package com.orderfood.springboot.seed.bz1.service.repository;

import com.orderfood.springboot.seed.bz1.service.entity.OrderCommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderCommentRepository extends CrudRepository<OrderCommentEntity, String> {
    /**
     * 根据订单人id 内表查询并且按照时间降序
     * @param orderId
     * @return
     */
    List<OrderCommentEntity> findByOrderIdInOrderByTimeDesc(List<String> orderId);
}
