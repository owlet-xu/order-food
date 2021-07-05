package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.OrderListInfo;
import com.orderfood.springboot.seed.bz1.contract.model.OrderMeanListInfo;
import com.orderfood.springboot.seed.bz1.service.entity.OrderListEntity;
import com.orderfood.springboot.seed.bz1.service.entity.OrderMeanListEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/9.
 */
@Mapper(componentModel = "spring")
public interface OrderlistMapper {
    /**
     *      订单的model转换到entity
     * @param orderListInfo
     * @return
     */
    OrderListEntity modelToEntity (OrderListInfo orderListInfo);

    /**
     *       订单列表里面的entity转化成model
     * @param orderListEntity
     * @return
     */
    OrderListInfo entityToModel (OrderListEntity orderListEntity);

    /**
     * @param orderListInfos
     * @return
     */
    List<OrderListEntity> modelstoEntities (List<OrderListInfo> orderListInfos);

    /**
     *    @param orderListEntities
     * @return
     */
    List<OrderListInfo> entitiestoModels(List<OrderListEntity> orderListEntities);

    /**
     * @param orderMeanListEntity
     * @return
     */
    OrderMeanListInfo entityToModel(OrderMeanListEntity orderMeanListEntity);

    /**
     * @param orderMeanListEntity
     * @return
     */
    List<OrderMeanListInfo> entitisToModes(List<OrderMeanListEntity> orderMeanListEntity);

}
