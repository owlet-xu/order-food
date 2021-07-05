package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.LoginInfo;
import com.orderfood.springboot.seed.bz1.contract.model.OrderUserInfo;
import com.orderfood.springboot.seed.bz1.service.entity.LoginEntity;
import com.orderfood.springboot.seed.bz1.service.entity.OrderUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by yangxuechao on 2018/7/5.
 */
@Mapper(componentModel = "spring")
public interface OrderUserMapper {

    /**
     *     将用户的OrderUserEntity转换成OrderUserInfo
     * @param orderUserEntity
     * @return
     */

    OrderUserInfo entityToModel (OrderUserEntity orderUserEntity);

    /**
     *     将用户的OrderUserInfo转换成OrderUserEntity
     * @param orderUserInfo
     * @return
     */
    @Mapping(target = "id", ignore = true)
    OrderUserEntity modelToEntity(OrderUserInfo orderUserInfo);

//    /**
//     * 将List<OrderUserInfo>转换成List<OrderUserEntity>
//     * @param orderUserInfos
//     * @return
//     */
//    List<OrderUserEntity> modelstoEntities (List<OrderUserInfo> orderUserInfos);

    /**
     *     将List<OrderUserEntity>转换成List<OrderUserInfo>
     * @param orderUserEntities
     * @return
     */
    List<OrderUserInfo> entitiestoModels  (List<OrderUserEntity> orderUserEntities);

    /**
     * 将LoginInfo转换成LoginEntity
     * @param loginInfo
     * @return
     */
//    LoginEntity modelToEntity(LoginInfo loginInfo);

    /**
     *    将LoginInfo转换成LoginEntity
     * @param loginInfo
     * @return
     */
    OrderUserEntity modelToEntity(LoginInfo loginInfo);

//    /**
//     * 将LoginEntity转换成LoginInfo
//     * @param loginEntity
//     * @return
//     */
//    LoginInfo entityToModel (LoginEntity loginEntity);
}
