package com.orderfood.springboot.seed.bz1.contract.service;

import com.orderfood.springboot.seed.bz1.contract.model.*;

import java.util.List;

/**
 * Created by xuguoyuan on 2018/7/9.
 */
public interface OrderListService {


    /**
     * 评价数的接口
     * @param orderCommentInfo
     * @return
     */
    Boolean save(OrderCommentInfo orderCommentInfo);

    /**
     * 根据id查询订单信息
     * @param id
     * @return
     */
    OrderListInfo findById(String id);


    /**
     *  订单分页查询
     * @param request
     * @return
     */
    OrderListPageRepose selectList (OrderListPageRequest request);


    /**
     * 查询订单子菜单信息
     * @param orderId
     * @return
     */
    List<OrderMeanListInfo> selectlist(String orderId);

    /**
     * 用户修改订单状态
     * @param id
     * @return
     */
    Boolean updateOrder(String id);

    /**
     * 商家确定或者取消订单
     * @param orderSellerInfo
     * @return
     */
    Boolean sellerUpdate(OrderSellerInfo orderSellerInfo);

    /**
     * 统计订单数据的接口
     * @param id
     * @return
     */
    int countOrder(String id);

    /**
     * 根据用户的查询订单的列表
     * @param id
     * @return
     */
    List<OrderListInfo> findByOrderId(String id);

    /**
     * 创建订单的
     * @param orderListInfo
     * @return
     */
    OrderListInfo saveAndFlush(OrderListInfo orderListInfo);

    /**
     * 统计今日或本月份数
     * @param flag
     * @return
     */
    int countNumber(String flag);

    /**
     * 统计今日或本月金额
     * @param flag
     * @return
     */
    double countMomey(String flag);

    /**
     *统计今天的订单数量和金额数量
     * @return
     */
    CountTodayInfo selectId ();

    /**
     * 这里是评价数据的接口
     * @param id
     * @return
     */
    List<OrderCommentInfo> commentlist(String id);


}
