package com.orderfood.springboot.seed.bz1.service.serviceimpl;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import com.orderfood.springboot.seed.bz1.contract.service.OrderFoodService;
import com.orderfood.springboot.seed.bz1.service.datamappers.OrderFoodMapper;
import com.orderfood.springboot.seed.bz1.service.entity.OrderFoodEntity;
import com.orderfood.springboot.seed.bz1.service.repository.OrderFoodRepository;
import com.orderfood.springboot.seed.common.qiqi.HttpRestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangxuechao on 2018/7/10.
 */
@Service

public class OrderFoodServiceImpl implements OrderFoodService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    final private  String status = "1";
    @Autowired
    OrderFoodMapper orderFoodMapper;

    @Autowired
    OrderFoodRepository orderFoodRepository;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public Boolean save(OrderFoodInfo orderFoodInfo) {
        OrderFoodEntity orderFoodEntity = orderFoodMapper.modelToEntity(orderFoodInfo);
        if("".equals(orderFoodEntity.getFoodName())||"".equals(orderFoodEntity.getFoodType())){
            logger.info("食物名称或者食物类型不能为空！");
            throw new HttpRestException(HttpStatus.BAD_REQUEST, "食物名称或者食物类型不能为空！");

        }

        orderFoodEntity.setAddTime(df.format(System.currentTimeMillis()));
        orderFoodEntity.setStatus(status);
        OrderFoodEntity orderFood = orderFoodRepository.save(orderFoodEntity);
        return true;
    }

    @Override
    public OrderPageRepose  selectList(OrderPageRequest request) {
        logger.debug("*************分页查询的数据***************");
        logger.debug(request.toString());
        logger.debug("**************分页查询的数据***************");
        if (null == request.getPageNum() || request.getPageNum() == 0) {
            request.setPageNum(1);
        }
        if (null == request.getPaSize() || request.getPaSize() == 0) {
            request.setPaSize(1000);
        }
        int pageNum = request.getPageNum() - 1;
        //默认按创建时间降序排序
        Sort sort = new Sort(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = new PageRequest(pageNum, request.getPaSize(),sort);
        Specification specification = new Specification() {


            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
                if(null!=request.getFoodName()&&!"".equals(request.getFoodName())){
                    predicates.add(cb.like(root.get("foodName"), "%" + request.getFoodName() + "%"));
                }
                if(null!=request.getFoodType()&&!request.getFoodType().equals("")){
                    predicates.add(cb.equal(root.get("foodType"), request.getFoodType()));
                }
                if(null!=request.getStatus()&&!"".equals(request.getStatus())){
                    predicates.add(cb.equal(root.get("status"), request.getStatus()));
                }
                if(0!=request.getMinPrice()){
                    predicates.add(cb.greaterThan(root.get("price"), request.getMinPrice()));
                }
                if(0!=request.getMaxPrice()){
                    predicates.add(cb.lessThan(root.get("price"), request.getMaxPrice()));
                }
                if("0".equals(request.getUserType())){
                    predicates.add(cb.equal(root.get("status"), "0"));
                }
                return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()]));
            }
        };

        Page all = orderFoodRepository.findAll(specification, pageRequest);
        OrderPageRepose orderPageRepose = new OrderPageRepose();
        List<OrderFoodInfo> list = orderFoodMapper.entitiesToModels(all.getContent());
        orderPageRepose.setContent(list);
        orderPageRepose.setTotal(all.getTotalPages());
        return orderPageRepose;
    }

    @Transactional
    @Override
    public Boolean update(OrderUpdateStatusInfo orderUpdateStatusInfo) {
        String id = orderUpdateStatusInfo.getId();
        String status = orderUpdateStatusInfo.getStatus();
        orderFoodRepository.updateStatus(status,id);
        return true;
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        orderFoodRepository.deleteById(id);
        return true;
    }


    @Override
    public OrderFoodInfo findById(String id) {
        OrderFoodEntity orderFoodEntity = orderFoodRepository.findById(id).orElse(null);
        if (null == orderFoodEntity) {
            orderFoodEntity = new OrderFoodEntity();
        }
        OrderFoodInfo orderFoodInfo = orderFoodMapper.entityToModel(orderFoodEntity);
        return orderFoodInfo;
    }

    @Override
    public FileMeta uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;

        while(((Iterator) itr).hasNext()){
            mpf = request.getFile(itr.next());
        }
        FileMeta fileMeta = new FileMeta();
        if(mpf == null) {
            fileMeta.setStatu(false);
            return fileMeta;
        }
        String oldName = mpf.getOriginalFilename();
        String[] oldNames = oldName.split("\\.");
        if ( oldNames.length < 2) {
            fileMeta.setStatu(false);
            fileMeta.setFileName("");
            return null;
        }
        String newName = Calendar.getInstance().getTimeInMillis()+"." + oldNames[1];
        logger.debug(newName+"新的照片名称");
        String fullPath = "D:/pictures/" + newName;

        fileMeta.setStatu(true);
        fileMeta.setFileName(newName);
        fileMeta.setFilesize(mpf.getSize()/1024 + "kb");
        fileMeta.setFileType(mpf.getContentType());
        fileMeta.setPath(fullPath);
        try {
            fileMeta.setBytes(mpf.getBytes());
            FileCopyUtils.copy(mpf.getBytes(),new FileOutputStream(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileMeta;
    }

}
