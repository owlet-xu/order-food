package com.orderfood.springboot.seed.bz1.service.serviceimpl;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import com.orderfood.springboot.seed.bz1.contract.service.OrderUserService;
import com.orderfood.springboot.seed.bz1.service.datamappers.OrderUserMapper;
import com.orderfood.springboot.seed.bz1.service.entity.LoginEntity;
import com.orderfood.springboot.seed.bz1.service.entity.OrderUserEntity;
import com.orderfood.springboot.seed.bz1.service.repository.OrderUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxuechao on 2018/7/5.
 */
@Service
public class OrderUserServiceImpl implements OrderUserService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    final private String flag = "0";

    @Autowired
    OrderUserRepository orderUserRepository;

    @Autowired
    OrderUserMapper orderUserMapper;


    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public OrderUserPageRepose selectlist(OrderUserPageRequest orderUserPageRequest) {
        if (null == orderUserPageRequest.getPageNum() || orderUserPageRequest.getPageNum() == 0) {
            orderUserPageRequest.setPageNum(1);
        }
        if (null == orderUserPageRequest.getPaSize() || orderUserPageRequest.getPaSize() == 0) {
            orderUserPageRequest.setPaSize(10);
        }
        int pageNum = orderUserPageRequest.getPageNum() - 1;
        //默认按创建时间排序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(pageNum, orderUserPageRequest.getPaSize(),sort);

        Specification specification = new Specification() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(null!=orderUserPageRequest.getName()&&!"".equals(orderUserPageRequest.getName())){
                    predicates.add(cb.like(root.get("name"), "%" + orderUserPageRequest.getName() + "%"));
                }
                if(null!=orderUserPageRequest.getPhone()&&!orderUserPageRequest.getPhone().equals("")){
                    predicates.add(cb.equal(root.get("phone"), orderUserPageRequest.getPhone()));
                }
                if(null!=orderUserPageRequest.getSex()&&!"".equals(orderUserPageRequest.getSex())){
                    predicates.add(cb.equal(root.get("status"), orderUserPageRequest.getSex()));
                }
                return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()]));
            }
        };
        Page all = orderUserRepository.findAll(specification,pageRequest);
        OrderUserPageRepose orderUserPageRepose = new OrderUserPageRepose();
        List<OrderUserInfo> list = orderUserMapper.entitiestoModels(all.getContent());
        orderUserPageRepose.setContent(list);
        orderUserPageRepose.setPageNum(all.getNumber()+1);
        orderUserPageRepose.setPageSize(all.getSize());
        orderUserPageRepose.setTotal(all.getTotalElements());
        return orderUserPageRepose;
    }

    @Override
    public Boolean update(OrderUserInfo orderUserInfo) {
        OrderUserEntity orderUserEntity=orderUserMapper.modelToEntity(orderUserInfo);
        orderUserEntity.setCreateTime(df.format(System.currentTimeMillis()));
        orderUserEntity.setId(orderUserInfo.getId());
        OrderUserEntity orderUser = orderUserRepository.saveAndFlush(orderUserEntity);
        return true;
    }

    public List<OrderUserInfo> findByPhone(String  phone) {
        List<OrderUserEntity> resultList = new ArrayList<>();
        Iterable<OrderUserEntity> all = orderUserRepository.findByPhone(phone);
        all.forEach(appUser -> resultList.add(appUser));
        return  orderUserMapper.entitiestoModels(resultList);
    }

    @Override
    public List<OrderUserInfo> findByEmail(String email) {
        List<OrderUserEntity> resultList = new ArrayList<>();
        Iterable<OrderUserEntity> all = orderUserRepository.findByEmail(email);
        all.forEach(appUser -> resultList.add(appUser));
        return  orderUserMapper.entitiestoModels(resultList);
    }

    @Override
    public OrderUserInfo save(LoginInfo loginInfo) {
        OrderUserEntity orderUser = null;
        OrderUserEntity loginEntity = orderUserMapper.modelToEntity(loginInfo);
        loginEntity.setCreateTime(df.format(System.currentTimeMillis()));
        loginEntity.setType(flag);
        logger.debug("登陆的实体信息！");
        logger.debug(loginEntity.toString());
        orderUser = orderUserRepository.save(loginEntity);
        return orderUserMapper.entityToModel(orderUser);

    }

    @Override
    public OrderUserInfo findById(String id) {
        OrderUserEntity orderListEntity = orderUserRepository.findById(id).orElse(new OrderUserEntity());
        OrderUserInfo orderListInfo = orderUserMapper.entityToModel(orderListEntity);
        return orderListInfo ;
    }


}
