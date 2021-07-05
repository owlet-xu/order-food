package com.orderfood.com.springboot.seed.bz1.controller;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import com.orderfood.springboot.seed.bz1.contract.service.OrderListService;
import com.orderfood.springboot.seed.common.qiqi.HttpRestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxuechao on 2018/7/9.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "AppMean Api")
public class OrderMeanListController {


    @Autowired
    OrderListService orderListService;



    /**
     * 这里是根据订单字表的foodId查询订单表里面的id,最后关联评价表里面的评价信息
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/commentlist/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "这里是根据订单字表的foodId查询订单表里面的id,最后关联评价表里面的评价信息",
            notes = "查询评价信息",response = OrderMeanListController.class
    )
    public ResponseData commentlist(@PathVariable String id) {
       ResponseData responseData = new ResponseData();
       Boolean flag = false;
       List<OrderCommentInfo> orderCommentInfoList = orderListService.commentlist(id);
       if(orderCommentInfoList.size()!=0){
           responseData.setSuccess(true);
           responseData.setMsg("查询成功!");
           responseData.setData(orderCommentInfoList);
           return responseData;
       }else{
           responseData.setSuccess(false);
           responseData.setMsg("查询失败!");
           return responseData;
       }
    }



    /**
     * 这里是评价的生成
     * @param orderCommentInfo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "创建一个评价",notes = "增加评价",response = OrderMeanListController.class
    )
    public ResponseData commentlist(@RequestBody OrderCommentInfo orderCommentInfo, BindingResult bindingResult) {
        if ((bindingResult.hasErrors())) {
            throw new HttpRestException(HttpStatus.BAD_REQUEST, "param valiad failed");
        }
        ResponseData responseData = new ResponseData();
        Boolean flag = false;
        flag = orderListService.save(orderCommentInfo);
        if(flag){
            responseData.setSuccess(true);
            responseData.setMsg("评价成功!");
            return responseData;
        }else{
            responseData.setSuccess(false);
            responseData.setMsg("评价失败!");
            return responseData;
        }
    }

    /**
     * 更新订单的状态，取消订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateOrder/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "删除问题",notes = "删除订单就是更新状态为0",response = OrderUserController.class
    )
    public ResponseData update(@PathVariable String id){
        ResponseData responseData = new ResponseData();
        Boolean status = orderListService.updateOrder(id);
        if(true==status){
            responseData.setMsg("修改成功!");
            responseData.setSuccess(true);
            return responseData;

        }else{
            responseData.setMsg("修改失败!");
            responseData.setSuccess(false);
            return responseData;
        }
    }


    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchorder",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "用户的分页查询",notes = "用户信息的分页查询",response = OrderUserController.class
    )
    public ResponseData list(@RequestBody(required = false) OrderListPageRequest request) {
        ResponseData responseData = new ResponseData();
        OrderListPageRepose orderListPageRepose = orderListService.selectList(request);
        if(null!=orderListPageRepose&&!"".equals(orderListPageRepose)){
            responseData.setData(orderListService.selectList(request));
            responseData.setSuccess(true);
            responseData.setMsg("查询成功！");
        }else{
            responseData.setSuccess(false);
            responseData.setMsg("查询失败！");
        }
        return responseData;

    }


    /**
     * 订单详细列表，单独根据id查询订单信息接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/searchorderlist/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "用户的分页查询",notes = "用户信息的分页查询",response = OrderUserController.class
    )
    public ResponseData searchorderlist(@PathVariable String id) {
        ResponseData responseData = new ResponseData();

        List<OrderMeanListInfo> orderMeanListInfo = orderListService.selectlist(id);
        responseData.setData(orderMeanListInfo);
        return responseData;
    }

    /**
     *  查询订单,包含字表信息
     * @return
     */
    @RequestMapping(value = "/findOrderList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "用户的分页查询",notes = "用户信息的分页查询",response = OrderUserController.class
    )
    public ResponseData findOrderList(@RequestBody(required = false) OrderListPageRequest request) {
        ResponseData responseData = new ResponseData();
//        订单的分页查询,查询出来的是一个封装好的分页对象
        OrderListPageRepose orderList = orderListService.selectList(request);
//        这里是最终封装的对象
        OrderDetailListPageInfo orderDetailListPageInfo = new OrderDetailListPageInfo();
        orderDetailListPageInfo.setPageNum(orderList.getPageNum());
        orderDetailListPageInfo.setPageSize(orderList.getPageSize());
        orderDetailListPageInfo.setTotal(orderList.getTotal());
        List<OrderDetailList> orderDetailListList = new ArrayList<>();
        for (OrderListInfo order:
                orderList.getContent()) {
            List<OrderMeanListInfo> orderMeanListInfoList = orderListService.selectlist(order.getId());
            // 封装子表信息
            OrderDetailList orderDetailList = new OrderDetailList();
            orderDetailList.setExpand(true);
            orderDetailList.setCreateTime(order.getCreateTime());
            orderDetailList.setId(order.getId());
            orderDetailList.setOrderAddress(order.getOrderAddress());
            orderDetailList.setOrderId(order.getOrderId());
            orderDetailList.setOrderName(order.getOrderName());
            orderDetailList.setOrderNu(order.getOrderNu());
            orderDetailList.setOrderPhone(order.getOrderPhone());
            orderDetailList.setSellerId(order.getSellerId());
            orderDetailList.setSellerName(order.getSellerName());
            orderDetailList.setSellerPhone(order.getSellerPhone());
            orderDetailList.setStatus(order.getStatus());
            orderDetailList.setTotalPrice(order.getTotalPrice());
            orderDetailList.setSellerRemark(order.getSellerRemark());
            orderDetailList.setOrderRemark(order.getOrderRemark());
            orderDetailList.setCount(order.getCount());
            // 把子表加进
            orderDetailList.setOrderDetail(orderMeanListInfoList);
            orderDetailListList.add(orderDetailList);
        }
        orderDetailListPageInfo.setOrderDetailListList(orderDetailListList);
        responseData.setSuccess(true);
        responseData.setMsg("查询成功");
        responseData.setData(orderDetailListPageInfo);
        return responseData;

    }

    /**
     * 用户确定或者拒接订单
     * @param orderSellerInfo
     * @return
     */
    @RequestMapping(value = "/sellerUpdate",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "商家确定或者拒接订单",notes = "商家确定或者拒绝订单",response = OrderUserController.class
    )
    public ResponseData sellerUpdate(@RequestBody(required = false) OrderSellerInfo orderSellerInfo) {
        ResponseData responseData = new ResponseData();
        if(!"3".equals(orderSellerInfo.getStatus())&&!"2".equals(orderSellerInfo.getStatus())){
            responseData.setMsg("取消状态数据格式不正确！取消‘3’,接单 ‘2’");
            responseData.setSuccess(false);
        }
        Boolean status =false;
        status = orderListService.sellerUpdate(orderSellerInfo);
        if(true==status){
            responseData.setSuccess(true);
            responseData.setMsg("修改成功!");
        }else{
            responseData.setSuccess(false);
            responseData.setMsg("修改失败!");
        }
        return responseData;
    }


    /**
     *  查询订单,包含字表信息
     * @return
     */
    @RequestMapping(value = "/message/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "用户和商家获取信息",notes = "用户和商家获取信息",response = OrderUserController.class
    )
    public ResponseData getMessage(@PathVariable(required = false)  String id) {
        ResponseData responseData = new ResponseData();
        OrderMessageInfo messageInfo = new OrderMessageInfo();
//       这里时统计订单数据量
        int num = orderListService.countOrder(id);
        List<OrderListInfo> orderListInfoList = orderListService.findByOrderId(id);
        Boolean flag = false;
        if(num==orderListInfoList.size()){
            messageInfo.setCountNumber(num);
            messageInfo.setListInfoList(orderListInfoList);
            responseData.setSuccess(true);
            responseData.setData(messageInfo);
            responseData.setMsg("查询成功!");
            return responseData;
        }else {
            responseData.setSuccess(false);
            responseData.setMsg("查询失败!");
            return responseData;
        }
    }


    /**
     * 创建订单信息
     * @param orderListInfo
     * @return
     */
    @RequestMapping(value = "/createOrderInfo",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "创建订单信息",notes = "购物车结算的接口",response = OrderUserController.class
    )
    public ResponseData countNumber(@RequestBody(required = false) OrderListInfo orderListInfo) {
        ResponseData responseData = new ResponseData();
        OrderListInfo orderList =orderListService.saveAndFlush(orderListInfo);
        Boolean b = false;
        if(null!=orderList&&!"".equals(orderList)){
            responseData.setMsg("创建成功!");
            responseData.setSuccess(true);
            responseData.setData(orderList);
        }else{
            responseData.setMsg("创建失败!");
            responseData.setSuccess(false);
        }
        return responseData;
    }


    /**
     * 统计当月的数量，0是当天的数量，1是本月的数量
     * @param flag
     * @return
     */
    @RequestMapping(value = "/countNumber/{flag}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "统计今日或本月订单数目",notes = "统计今日或本月订单数目",response = OrderUserController.class
    )
    public ResponseData countNumber(@PathVariable String flag) {
        ResponseData res = new ResponseData();
        int num  = orderListService.countNumber(flag);
        res.setSuccess(true);
        res.setMsg("统计成功");
        res.setData(num);
        return res;
    }

    /**
     * 统计当天或者本月的金额，标志位0是今天，1是当月
     * @param flag
     * @return
     */
    @RequestMapping(value = "/countMoney/{flag}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "统计今日或本月订单金额",notes = "统计今日或本月订单金额",response = OrderUserController.class
    )
    public ResponseData countMoney(@PathVariable String flag) {
        ResponseData res = new ResponseData();
        double money  = orderListService.countMomey(flag);
        res.setSuccess(true);
        res.setMsg("统计成功");
        res.setData(money);
        return res;
    }


    /**
     * 封装实体，统计今天的今天的金额和今天的数量
     * @return
     */
    @RequestMapping(value = "/countToday",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "区分所有的菜品数量",notes = "区分所有的菜品数量",response = OrderUserController.class
    )
    public ResponseData countToday() {
        ResponseData responseData = new ResponseData();
        CountTodayInfo  res = orderListService.selectId();
        responseData.setSuccess(true);
        responseData.setData(res);
        responseData.setMsg("统计成功");
        return responseData;
    }

}
