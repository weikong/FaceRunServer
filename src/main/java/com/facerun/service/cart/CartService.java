package com.facerun.service.cart;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 * 购物车
 */
@Service
@Transactional
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CustCartMapper custCartMapper;

    /**
     * 水果
     * 查询购物车
     * */
    public Object queryFruitList(Map params){
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        List<Map> list = custCartMapper.queryFruitCartById(params);
        return list;
    }

    /**
     * 水果
     * 加入购物车
     * */
    public Object createFruitCart(Map params){
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        int product_id = MapUtils.getInteger(params, "product_id",-1);
        int address_id = MapUtils.getInteger(params, "address_id",-1);
        String price_type = MapUtils.getString(params, "price_type","");
        int price = MapUtils.getInteger(params, "price",-1);
        if (account_id <= 0 || product_id <= 0 || StringUtils.isEmpty(price_type))
            throw new BizException(Code.PARAMS_MISS);
        Cart cart = new Cart();
        cart.setAccountid(account_id);
        cart.setProductid(1);
        cart.setAddressid(1);
        cart.setPricetype("1");
        cart.setPrice(BigDecimal.valueOf(25));
        cart.setCount(2);
        int insert = cartMapper.insertSelective(cart);
        if (insert != 1){
            throw new BizException(Code.FAIL_DATABASE_INSERT);
        }
        return insert;
    }

    /**
     * 水果
     * 删除购物车中某条数据
     * */
    public Object delFruitById(Map params){
        int cart_id = MapUtils.getInteger(params, "cart_id",-1);
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        if (cart_id <= 0 || account_id <= 0)
            throw new BizException(Code.PARAMS_MISS);
        int del = cartMapper.deleteByPrimaryKey(cart_id);
        if (del != 1){
            throw new BizException(Code.FAIL_DATABASE_DEL);
        }
        return del;
    }
}
