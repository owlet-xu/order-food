package com.orderfood.springboot.seed.bz1.contract.service;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by yangxuechao on 2018/7/10.
 */
public interface OrderFoodService {
    /**
     * 上传菜品
     * @param orderFoodInfo
     * @return
     */
    Boolean save(OrderFoodInfo orderFoodInfo);

    /**
     * 查询菜品
     * @param request
     * @return
     */
    OrderPageRepose selectList (OrderPageRequest request);

    /**
     * 菜品的上架和下架
     * @param orderUpdateStatusInfo
     * @return
     */
    Boolean update (OrderUpdateStatusInfo orderUpdateStatusInfo);

    /**
     * 删除菜品
     * @param id
     * @return
     */
    Boolean delete (String id);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    OrderFoodInfo findById(String id);

    /**
     * 上传文件的接口
     * @param request
     * @param response
     * @return
     */
    FileMeta uploadFile(MultipartHttpServletRequest request, HttpServletResponse response);
}
