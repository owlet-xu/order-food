package com.orderfood.springboot.seed.bz1.service.datamappers;

import com.orderfood.springboot.seed.bz1.contract.model.OrderCommentInfo;
import com.orderfood.springboot.seed.bz1.service.entity.OrderCommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderCommentMapper {
    /**
     *      @param orderFoodInfo
     * @return
     */
    OrderCommentEntity modelToEntity (OrderCommentInfo orderFoodInfo);


    /**
     * @param orderCommentEntity
     * @return
     */
    @Mapping(target = "id",ignore = true)
    OrderCommentInfo entityToModel (OrderCommentEntity orderCommentEntity);

//    /**
//     * @param orderCommentInfoList
//     * @return
//     */
//    List<OrderCommentEntity> modelstoEntities(List<OrderCommentInfo> orderCommentInfoList);

    /**
     *     评价信息查询里面的实体列表转化为实体model
     * @param orderCommentEntityList
     * @return
     */
    List<OrderCommentInfo> entitiestoModels(List<OrderCommentEntity> orderCommentEntityList);

}
