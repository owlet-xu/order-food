package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.OrderFoodInfo;
import com.orderfood.springboot.seed.bz1.service.entity.OrderFoodEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/10.
 */
@Mapper(componentModel = "spring")
public interface OrderFoodMapper {

    OrderFoodInfo entityToModel(OrderFoodEntity orderfoodEntity);

    OrderFoodEntity modelToEntity (OrderFoodInfo orderFoodInfo);

    List<OrderFoodEntity> modelsToEntities (List<OrderFoodInfo> orderFoodInfos);

    List<OrderFoodInfo>  entitiesToModels (List<OrderFoodEntity> orderFoodEntities);
}
