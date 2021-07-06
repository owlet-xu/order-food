package com.orderfood.springboot.seed.bz1.service.serviceimpl;

import com.orderfood.springboot.seed.bz1.contract.model.*;
import com.orderfood.springboot.seed.bz1.contract.service.OrderListService;
import com.orderfood.springboot.seed.bz1.service.datamappers.OrderCommentMapper;
import com.orderfood.springboot.seed.bz1.service.datamappers.OrderMeanListMapper;
import com.orderfood.springboot.seed.bz1.service.datamappers.OrderlistMapper;
import com.orderfood.springboot.seed.bz1.service.entity.*;
import com.orderfood.springboot.seed.bz1.service.repository.OrderCommentRepository;
import com.orderfood.springboot.seed.bz1.service.repository.OrderListRepository;
import com.orderfood.springboot.seed.bz1.service.repository.OrderMeanlistRepository;
import com.orderfood.springboot.seed.bz1.service.repository.OrderUserRepository;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by xuguoyuan on 2018/7/9.
 */
@Service
public class OrderListImpl implements OrderListService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    final private String sellerId = "2c92889664ab75740164ab76943b0000";
    final private String sellerName = "辰安小徐食堂";
    final private String sellerPhone = "17775338917";
    final private String status = "1";
    final private String cancel = "0";



    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    OrderMeanlistRepository orderMeanlistRepository;

    @Autowired
    OrderUserRepository userRepository;

    @Autowired
    OrderCommentRepository orderCommentRepository;

    @Autowired
    OrderlistMapper orderlistMapper;

    @Autowired
    OrderCommentMapper orderCommentMapper;

    @Autowired
    OrderMeanListMapper orderMeanListMapper;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //  获取今天的时间,省的截取
    SimpleDateFormat getDf = new SimpleDateFormat("yyyy-MM-dd");
    //  获取本月的时间
    SimpleDateFormat getDfMouth = new SimpleDateFormat("yyyy-MM");

    final String today = getDf.format(System.currentTimeMillis());
    final String mouth = getDfMouth.format(System.currentTimeMillis());
    SimpleDateFormat nu = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public Boolean save(OrderCommentInfo orderCommentInfo) {
        OrderCommentEntity orderCommentEntity = orderCommentMapper.modelToEntity(orderCommentInfo);
//        这里是根据评价信息查询用户姓名
        String id = orderCommentInfo.getEvaluateId();
        OrderUserEntity  orderUserEntity = userRepository.findById(id).orElse(new OrderUserEntity());
//        这里是根据查询到的用户姓值到评价表里面
        orderCommentEntity.setEvaluateName(orderUserEntity.getName());

        orderCommentEntity.setTime(df.format(System.currentTimeMillis()));
        OrderCommentEntity orderComment = orderCommentRepository.save(orderCommentEntity);


        return true;
    }

    @Override
    public OrderListInfo findById(String id) {
        return null;
    }


//    订单分页查询实现类
    @Override
    public OrderListPageRepose selectList(OrderListPageRequest request) {

        if (null == request.getPageNum() || request.getPageNum() == 0) {
            request.setPageNum(1);
        }
        if (null == request.getPaSize() || request.getPaSize() == 0) {
            request.setPaSize(10);
        }
        int pageNum = request.getPageNum() - 1;
        //默认按创建时间排序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(pageNum, request.getPaSize(),sort);

        Specification specification = new Specification() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(null!=request.getName()&&!"".equals(request.getName())){
                    predicates.add(cb.like(root.get("orderName"), "%" + request.getName() + "%"));
                }
                if(null!=request.getAddress()&&!request.getAddress().equals("")){
                    predicates.add(cb.like(root.get("orderAddress"), "%" + request.getAddress() + "%"));
                }
                if(null!=request.getNum()&&!request.getNum().equals("")){
                    predicates.add(cb.like(root.get("orderNu"), "%" + request.getNum() + "%"));
                }
                if(null!=request.getStatus()&&!"".equals(request.getStatus())){
                    predicates.add(cb.equal(root.get("status"), request.getStatus()));
                }
                if (null != request.getBeginTime()&&!"".equals(request.getBeginTime())) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime"), request.getBeginTime()));

                }
                if (null != request.getEndTime()&&!"".equals(request.getEndTime())) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createTime"), request.getEndTime()));
                }
//                用户消息权限区分
                if("0".equals(request.getType())){
                    predicates.add(cb.equal(root.get("orderId"), request.getUserId()));
                }
//                开始结束时间
                return cb.and(predicates.toArray(new javax.persistence.criteria.Predicate[predicates.size()]));
            }
        };
        Page all = orderListRepository.findAll(specification,pageRequest);
//        OrderUserPageRepose orderUserPageRepose = new OrderUserPageRepose();
        OrderListPageRepose orderListPageRepose = new OrderListPageRepose();
        List<OrderListInfo> list = orderlistMapper.entitiestoModels(all.getContent());
        orderListPageRepose.setContent(list);
        orderListPageRepose.setPageNum(all.getNumber()+1);
        orderListPageRepose.setPageSize(all.getSize());
        orderListPageRepose.setTotal(all.getTotalElements());
        return orderListPageRepose;
    }

    @Override
    public List<OrderMeanListInfo> selectlist(String orderId) {
        List<OrderMeanListEntity> resultList = new ArrayList<>();
        Iterable<OrderMeanListEntity> all = orderMeanlistRepository.findByOrderId(orderId);
        all.forEach(appUser -> resultList.add(appUser));
        return orderlistMapper.entitisToModes(resultList);
    }

    @Override
    public Boolean updateOrder(String id) {
        OrderListEntity orderListEntity;
        orderListEntity = orderListRepository.findById(id).orElse(new OrderListEntity());
        orderListEntity.setStatus(cancel);
        orderListRepository.saveAndFlush(orderListEntity);
        return true;
    }

    @Override
    public Boolean sellerUpdate(OrderSellerInfo orderSellerInfo) {
        OrderListEntity orderListEntity = orderListRepository.findById(orderSellerInfo.getOrderId()).orElse(new OrderListEntity());
        orderListEntity.setSellerRemark(orderSellerInfo.getSellerRemark());
        orderListEntity.setStatus(orderSellerInfo.getStatus());
        orderListRepository.saveAndFlush(orderListEntity);
        return true;
    }

    @Override
    public int countOrder(String id) {
        if(null == id || "".equals(id) || "undefined".equals(id)){
            return 0;
        }
        OrderUserEntity orderUserEntity = userRepository.findById(id).orElse(new OrderUserEntity());
        if("0".equals(orderUserEntity.getType())){
            return  orderListRepository.countByOrderIdAndStatus(id,"1");
        }else {
            return  orderListRepository.countBySellerIdAndStatus(id,"1");
        }
    }

    @Override
    public List<OrderListInfo> findByOrderId(String id) {
        if(null == id || "".equals(id) || "undefined".equals(id)){
            return  new ArrayList<OrderListInfo>();
        }
        List<OrderListEntity> resultList = new ArrayList<>();
        OrderUserEntity orderUserEntity = userRepository.findById(id).orElse(new OrderUserEntity());
        Iterable<OrderListEntity> all = null;
        if("0".equals(orderUserEntity.getType())){
            all = orderListRepository.findByOrderIdAndStatus(id,"1");
        }else{
            all = orderListRepository.findBySellerIdAndStatus(id,"1");
        }
        all.forEach(appUser -> resultList.add(appUser));
        return orderlistMapper.entitiestoModels(resultList);
    }

    @Override
    public OrderListInfo saveAndFlush(OrderListInfo orderInfo) {
        int totalPrice = 0;
        int num = (int) (Math.random()*100);
        OrderListEntity orderListEntity = orderlistMapper.modelToEntity(orderInfo);
        orderListEntity.setCreateTime(df.format(System.currentTimeMillis()));
//        订单号时当前时间加上两位随机数
        orderListEntity.setOrderNu(nu.format(System.currentTimeMillis())+String.valueOf(num));
        orderListEntity.setStatus(status);
        orderListEntity.setSellerId(sellerId);
        orderListEntity.setSellerName(sellerName);
        orderListEntity.setSellerPhone(sellerPhone);
        OrderListEntity orderList = orderListRepository.saveAndFlush(orderListEntity);
        OrderListInfo orderListInfo = orderlistMapper.entityToModel(orderList);
        for (OrderMeanListInfo orderMeanListInfo: orderInfo.getOrderMeanListInfoList()) {
            OrderMeanListEntity orderMeanListEntity = orderMeanListMapper.modelToEntity(orderMeanListInfo);
//            后台价格校验
            totalPrice+=orderMeanListEntity.getPrice()*orderMeanListEntity.getCount();
            orderMeanListEntity.setOrderId(orderListInfo.getId());
            orderMeanListEntity.setType(orderMeanListInfo.getType());
            orderMeanListEntity.setAddTime(df.format(System.currentTimeMillis()));
            orderMeanlistRepository.save(orderMeanListEntity);
        }
        if(totalPrice!=Integer.valueOf(orderInfo.getTotalPrice())){
            throw new HttpRestException(HttpStatus.BAD_REQUEST, "价格不正确!");
        }
        return orderListInfo;
    }


    @Override
    public CountTodayInfo  selectId() {
//        封装一个实体，里面有两个map，
        Map<String,Double> countMoneys = new HashMap<>();
        Map<String,Integer> countNumber = new HashMap<>();

        List<OrderListEntity> resultList = new ArrayList<>();
        List<OrderListEntity> all = orderListRepository.findByCreateTimeLike(today+"%");
        all.forEach(appUser -> resultList.add(appUser));
        for (OrderListEntity id : resultList
                ) {
            //        根据id查询所有的菜品类型
            List<OrderMeanListEntity> orderMeanListEntityList = orderMeanlistRepository.findByOrderId(id.getId());
//           这里开始将数据放到map里面，如果存在key那么数据就直接加上，否则产生新的
            for (OrderMeanListEntity orderMeanListEntity: orderMeanListEntityList
                    ) {
                if(countMoneys.containsKey(orderMeanListEntity.getType())){
                    Double old = countMoneys.get(orderMeanListEntity.getType());
                    old += orderMeanListEntity.getPrice();
                    countMoneys.put(orderMeanListEntity.getType(),old);
                }else {
                    countMoneys.put(orderMeanListEntity.getType(), orderMeanListEntity.getPrice());
                }
//                如果存在某一种菜品类型，那么就，value就累计
                if(countNumber.containsKey(orderMeanListEntity.getType())){
                    Integer num = countNumber.get(orderMeanListEntity.getType());
                    ++num;
                    countNumber.put(orderMeanListEntity.getType(),num);
                }else {
                    countNumber.put(orderMeanListEntity.getType(), 1);
                }
            }
        }
        CountTodayInfo res = new CountTodayInfo();
        res.setCountMoneys(countMoneys);
        res.setCountNumber(countNumber);
        return res;
    }

    @Override
    public List<OrderCommentInfo> commentlist(String id) {
        List<OrderCommentEntity> resultList = new ArrayList<>();
//        传进来是菜的id
        List<OrderMeanListEntity> orderMeanListEntityList = orderMeanlistRepository.findByFoodId(id);
        // 获取菜品关联的订单id
        List<String> orderIds = new ArrayList<>();
        for (OrderMeanListEntity orderMeanListEntity:orderMeanListEntityList
             ) {
//            放到list里,最后使用JPA自带的in方法查询
            orderIds.add(orderMeanListEntity.getOrderId());
        }
        Iterable<OrderCommentEntity> all = orderCommentRepository.findByOrderIdInOrderByTimeDesc(orderIds);
        all.forEach(appUser -> resultList.add(appUser));
        List<OrderCommentInfo> back = orderCommentMapper.entitiestoModels(resultList);
        for ( OrderCommentInfo item : back) {
            // 查询用户信息,最后再把头像地址赋值给评价信息的model
            OrderUserEntity orderUserEntity = userRepository.findById(item.getEvaluateId()).orElse(null);
            if (null == orderUserEntity) {
                orderUserEntity = new OrderUserEntity();
            }
            item.setHeadImg(orderUserEntity.getHeadImg());
        }
        return back;
    }

    @Override
    public int countNumber(String falg) {
        int countNum = 0;
        if (0==Integer.valueOf(falg)){
            countNum = orderListRepository.countByStatusAndCreateTimeLike("2","%"+today+"%");
        }else{
            countNum = orderListRepository.countByStatusAndCreateTimeLike("2",mouth+"%");
        }
        return countNum;
    }

    /**
     * 状态 0取消 1等待接单 2接单 3用户取走
     * 统计就是统计已经接单状态的
     * @param flag
     * @return
     */
    @Override
    public double countMomey(String flag) {
        int countMoney = 0;
        if(0==Integer.valueOf(flag)){
            countMoney = orderListRepository.sumTotalPriceByStatusAndAndCreateTime("2",today+"%");
        }else{
            countMoney = orderListRepository.sumTotalPriceByStatusAndAndCreateTime("2",mouth+"%");
        }
        return countMoney;
    }

}
