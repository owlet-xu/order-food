package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.OrderMeanListInfo;
import com.orderfood.springboot.seed.bz1.service.entity.OrderMeanListEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by xuguoyuan on 2018/7/9.
 */
@Mapper(componentModel = "spring")
public interface OrderMeanListMapper {
    /**
     * 订单详细列表entity转化到model
     * @param orderMeanListEntity
     * @return
     */
    OrderMeanListInfo entityToModel (OrderMeanListEntity orderMeanListEntity);

    /**
     *      model的list转换到
     * @param orderMeanListInfo
     * @return
     */
    OrderMeanListEntity modelToEntity (OrderMeanListInfo orderMeanListInfo);

    /**
     * list下面的菜单详细model转换到实体
     * @param orderMeanListInfos
     * @return
     */
    List<OrderMeanListEntity> modelstoEntities (List<OrderMeanListInfo> orderMeanListInfos);

    /**
     * list下面的菜单详细转换成model
     * @param orderMeanListEntities
     * @return
     */
    List<OrderMeanListInfo> entitiestoModels (List<OrderMeanListEntity> orderMeanListEntities);
}
