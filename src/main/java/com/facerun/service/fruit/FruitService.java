package com.facerun.service.fruit;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.*;
import com.facerun.dao.*;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class FruitService {
    private static final Logger log = LoggerFactory.getLogger(FruitService.class);

    @Autowired
    private FruitMapper fruitMapper;
    @Autowired
    private CustFruitMapper custFruitMapper;
    @Autowired
    private FruitPriceTypeMapper fruitPriceTypeMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询水果商品列表
     * */
    public Object queryList(Map params){
        FruitExample example = new FruitExample();
        List<Fruit> list = fruitMapper.selectByExample(example);
        return list;
    }

    /**
     * 查询单个水果商品详情
     * */
    public Object queryDetailById(Map params){
        int id = MapUtils.getInteger(params, "id",-1);
        if (id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        Map map = new HashMap();
        map.put("id",id);
        List<Map> list = custFruitMapper.queryFruitById(map);
        if (list != null && list.size() == 1){
            Map item = list.get(0);
            FruitPriceTypeExample example = new FruitPriceTypeExample();
            example.createCriteria().andFruitidEqualTo(id);
            List<FruitPriceType> types = fruitPriceTypeMapper.selectByExample(example);
            item.put("fpricetypes",types);
            try {
                List<Address> addresses = new ArrayList<>();
                int account_id = MapUtils.getInteger(params, "account_id",-1);
                if (account_id > 0){
                    AddressExample addressExample = new AddressExample();
                    addressExample.createCriteria().andAccountidEqualTo(account_id);
                    addresses = addressMapper.selectByExample(addressExample);
                }
                item.put("address",addresses);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 创建订单
     * */
    public Object createOrder(Map params){
        return null;
    }

    /**
     * 查询订单
     * */
    public Object queryOrderById(Map params){
        int order_id = MapUtils.getInteger(params, "order_id",-1);
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (order_id <= 0 || account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        Map map = new HashMap();
        map.put("order_id",order_id);
        map.put("account_id",account_id);
        List<Map> list = custFruitMapper.queryFruitOrderById(map);
        map.clear();
        map.put("order",list);
        return map;
    }

    /**
     * 删除订单
     * */
    public Object delOrderById(Map params){
        int order_id = MapUtils.getInteger(params, "order_id",-1);
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (order_id <= 0 || account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        OrderItemExample itemExample = new OrderItemExample();
        itemExample.createCriteria().andOrderidEqualTo(order_id);
        List<OrderItem> list = orderItemMapper.selectByExample(itemExample);
        if (list != null && list.size() > 0){
            for (OrderItem item : list){
                int del = orderItemMapper.deleteByPrimaryKey(item.getId());
                if (del != 1){
                    throw new BizException(Code.FAIL_DATABASE_DEL);
                }
            }
        }
        int delOrder = orderMapper.deleteByPrimaryKey(order_id);
        if (delOrder != 1){
            throw new BizException(Code.FAIL_DATABASE_DEL);
        }
        return delOrder;
    }
}
