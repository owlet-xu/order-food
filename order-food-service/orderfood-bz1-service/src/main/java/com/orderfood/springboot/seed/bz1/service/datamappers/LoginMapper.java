package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.LoginInfo;
import com.orderfood.springboot.seed.bz1.contract.model.OrderUserInfo;
import com.orderfood.springboot.seed.bz1.service.entity.LoginEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/6.
 */
@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginEntity modelToEntity (LoginInfo loginInfo );
    LoginInfo entityToModel (LoginEntity loginEntity);

    List<LoginEntity> modelstoEntities(List<LoginInfo> loginInfos);

    List<LoginInfo> entitiestoModels (List<LoginEntity> loginEntities);

    LoginEntity modelToEntity(OrderUserInfo orderUserInfo);
}
