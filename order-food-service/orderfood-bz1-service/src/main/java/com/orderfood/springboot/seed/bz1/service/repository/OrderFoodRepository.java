package com.orderfood.springboot.seed.bz1.service.repository;

import com.orderfood.springboot.seed.bz1.service.entity.OrderFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yangxuechao on 2018/7/10.
 */
public interface OrderFoodRepository  extends JpaRepository<OrderFoodEntity,String>,JpaSpecificationExecutor<OrderFoodEntity> {
    /**
     * 自定义sql，@Modifying数据变动的时候需要加的
     * 使用的时候使用@Param注解的时候参数的名字需要一一对应
     * @param status
     * @param id
     * @return
     */
    @Modifying
    @Query("update OrderFoodEntity t set t.status =?1  where t.id= ?2")
    Integer updateStatus (String status, String id);

}
