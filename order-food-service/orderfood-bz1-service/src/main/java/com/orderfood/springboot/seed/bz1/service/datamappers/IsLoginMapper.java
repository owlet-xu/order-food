package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.IsLoginInfo;
import com.orderfood.springboot.seed.bz1.contract.model.OrderUserInfo;
import com.orderfood.springboot.seed.bz1.service.entity.OrderUserEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by xuguoyuan on 2018/7/6.
 */
@Mapper(componentModel = "spring")
public interface IsLoginMapper {
    OrderUserInfo entityToModel(OrderUserEntity orderUserEntity);

    OrderUserEntity modelToEntity (OrderUserInfo orderUserInfo);

    List<OrderUserEntity> modelsEntities (List<OrderUserInfo> orderUserInfos);

    List<OrderUserInfo>  entitiestoModels (List<OrderUserEntity> orderUserEntities);
}
