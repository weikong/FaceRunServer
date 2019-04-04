package com.facerun.controller.cart;


import com.facerun.controller.AbsController;
import com.facerun.exception.BizException;
import com.facerun.service.cart.CartService;
import com.facerun.service.fruit.FruitService;
import com.facerun.util.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 购物车
 */
@RequestMapping("/cart")
@RestController
public class CartController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    /**
     * 水果
     * 查询购物车
     * */
    @RequestMapping(value = "/query_list_fruit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object queryFruitList(@RequestParam Map params, Model model) {
        try {
            return ajax(cartService.queryFruitList(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     * 水果
     * 加入购物车
     * */
    @RequestMapping(value = "/creat_fruit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object createFruitCart(@RequestParam Map params, Model model) {
        try {
            return ajax(cartService.createFruitCart(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     * 水果
     * 删除购物车中某条数据
     * */
    @RequestMapping(value = "/del_fruit_by_id", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object delFruitById(@RequestParam Map params, Model model) {
        try {
            return ajax(cartService.delFruitById(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }
}
