package com.facerun.service.cart;

import com.facerun.bean.Cart;
import com.facerun.bean.CartExample;
import com.facerun.bean.FruitPriceType;
import com.facerun.dao.CartMapper;
import com.facerun.dao.CustCartMapper;
import com.facerun.dao.FruitPriceTypeMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private FruitPriceTypeMapper fruitPriceTypeMapper;

    /**
     * 水果
     * 查询购物车
     * */
    public Object queryFruitCarts(Map params){
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
        int count = MapUtils.getInteger(params, "count",1);
        int price_type = MapUtils.getInteger(params, "price_type",-1);
        if (account_id <= 0 || product_id <= 0 || price_type <= 0 || count <= 0)
            throw new BizException(Code.PARAMS_MISS);
        CartExample example = new CartExample();
        example.createCriteria().andAccountidEqualTo(account_id).andProductidEqualTo(product_id).andPricetypeEqualTo(""+price_type);
        List<Cart> list = cartMapper.selectByExample(example);
        Cart cart = null;
        if (list != null && list.size() > 0){
            cart = list.get(0);
            cart.setCount(cart.getCount()+1);
            int update = cartMapper.updateByPrimaryKey(cart);
            if (update != 1){
                throw new BizException(Code.FAIL_DATABASE_UPDATE);
            }
        } else {
            cart = new Cart();
            cart.setAccountid(account_id);
            cart.setProductid(product_id);
            cart.setPricetype(""+price_type);
            if (price_type > 0){
                FruitPriceType fruitPriceType = fruitPriceTypeMapper.selectByPrimaryKey(price_type);
                cart.setPrice(fruitPriceType.getPrice());
            }
            cart.setCount(count);
            int insert = cartMapper.insertSelective(cart);
            if (insert != 1){
                throw new BizException(Code.FAIL_DATABASE_INSERT);
            }
        }

        return cart;
    }

    /**
     * 水果
     * 更新购物车
     * */
    public Object updateFruitCart(Map params){
        int account_id = MapUtils.getInteger(params, "account_id",-1);
        int product_id = MapUtils.getInteger(params, "product_id",-1);
        int count = MapUtils.getInteger(params, "count",-1);
        int cart_id = MapUtils.getInteger(params, "cart_id",-1);
        if (account_id <= 0 || product_id <= 0 || cart_id <= 0 || count <= 0)
            throw new BizException(Code.PARAMS_MISS);
        Cart cart = cartMapper.selectByPrimaryKey(cart_id);
        if (cart != null && cart.getAccountid() == account_id && cart.getProductid() == product_id){
            cart.setCount(count);
            int update = cartMapper.updateByPrimaryKeySelective(cart);
            if (update != 1){
                throw new BizException(Code.FAIL_DATABASE_UPDATE);
            }
        } else {
            throw new BizException(Code.DATA_ERROR);
        }
        return cart;
    }

    /**
     * 水果
     * 删除购物车中某条数据
     * */
    public Object delCartFruitById(Map params){
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

    /**
     * 水果
     * 删除购物车中某条数据
     * */
    public int queryCartNumByAccountid(int account_id){
        int myCartNum = 0;
        if (account_id > 0){
            CartExample example = new CartExample();
            example.createCriteria().andAccountidEqualTo(account_id);
            List<Cart> list = cartMapper.selectByExample(example);
            for (Cart cart : list){
                if (cart != null){
                    myCartNum += cart.getCount();
                }
            }
        }
        return myCartNum;
    }
}
