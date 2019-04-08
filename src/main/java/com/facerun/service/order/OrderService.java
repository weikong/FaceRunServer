package com.facerun.service.order;

import com.facerun.bean.Orders;
import com.facerun.bean.OrdersExample;
import com.facerun.dao.OrdersMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrdersMapper ordersMapper;

    private List<Orders> queryOrders(Map params){
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        OrdersExample example = new OrdersExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        List<Orders> list = ordersMapper.selectByExample(example);
        return list;
    }

    /**
     * 查询水果商品列表
     * */
    @Cacheable("orders")
    public Object queryList(Map params){
        return queryOrders(params);
    }

    @CachePut("orders")
    public Object queryOrdersList(Map params){
        return queryOrders(params);
    }

    /**
     * 创建订单
     * */
    public Object createOrder(Map params){
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        Orders order = new Orders();
        order.setAccountId(account_id);
        int insert = ordersMapper.insertSelective(order);
        if (insert != 1){
            throw new BizException(Code.FAIL_DATABASE_INSERT);
        }
        return order;
    }

    /**
     * 删除订单
     * */
    public Object delOrderById(Map params){
        int order_id = MapUtils.getInteger(params, "order_id",-1);
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        if (order_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        OrdersExample example = new OrdersExample();
        example.createCriteria().andAccountIdEqualTo(account_id).andIdEqualTo(order_id);
        int delOrder =  ordersMapper.deleteByExample(example);
        if (delOrder != 1){
            throw new BizException(Code.FAIL_DATABASE_DEL);
        }
        return delOrder;
    }
}
