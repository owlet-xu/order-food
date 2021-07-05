package com.orderfood.springboot.seed.bz1.service.repository;

import com.orderfood.springboot.seed.bz1.contract.model.OrderListInfo;
import com.orderfood.springboot.seed.bz1.service.entity.OrderListEntity;
import com.orderfood.springboot.seed.bz1.service.entity.OrderMeanListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * JPA的方法应用
 * Created by yangxuechao on 2018/7/9.
 */
public interface OrderListRepository extends JpaRepository<OrderListEntity,String>,JpaSpecificationExecutor<OrderListEntity> {

   /**
    * 根据对象foodId获取订单对象的列表
    * @param id
    * @return
    */
//   List<OrderListEntity> findByFoodId(String id);

   /**
    * 根据订单状态和用户的id查询订单的对象
    * @param id
    * @param status
    * @return
    */
   List<OrderListEntity> findByOrderIdAndStatus(String id,String status);


   /**
    * 根据卖家的id和状态查询订单的实体
    * @param id
    * @param status
    * @return
    */
   List<OrderListEntity> findBySellerIdAndStatus(String id,String status);


   /**
    * 根据用户的id 和订单的状态来统计数量
    * @param id
    * @param status
    * @return
    */
   int countByOrderIdAndStatus(String id,String status);


   /**
    * 根据商家的id和订单的状态统计数量
    * @param id
    * @param status
    * @return
    */
   int countBySellerIdAndStatus(String id,String status);


   /**
    * 根据传入的时间统计状态时已经接单的数量
    * @param id
    * @param time
    * @return
    */
   int countByStatusAndCreateTimeLike(String id,String time);


   /**
    * 根据传入进来的时间，统计当月或者今天的总共价格，
    * 自定义实体类型的sql并且进行模糊查询，并且使用模糊查询写法
    * coalesce相当于Oracle里面的nvl，解决空值问题
    * @param status
    * @param time
    * @return
    */
   @Query("select coalesce(sum(t.totalPrice),0) as totalPrice from OrderListEntity t where t.status=:status and t.createTime like :time")
   int sumTotalPriceByStatusAndAndCreateTime(@Param("status")String status, @Param("time")String time);


   /**
    * 根据创建时间查询订单对象
    * @param time
    * @return
    */
   List<OrderListEntity> findByCreateTimeLike(String time);



}
