package com.facerun.controller.fruit;


import com.facerun.bean.*;
import com.facerun.controller.AbsController;
import com.facerun.dao.*;
import com.facerun.exception.BizException;
import com.facerun.service.fruit.FruitService;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("/fruit")
@RestController
public class FruitController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(FruitController.class);

    @Autowired
    private FruitService fruitService;


    /**
     * 查询商品列表
     */
    @RequestMapping(value = "/query/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object fruitList(@RequestParam Map params, Model model) {
        try {
            return ajax(fruitService.queryList(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     * 通过商品id，查询商品详情
     */
    @RequestMapping(value = "/query/detail_by_id", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object fruitDetailById(@RequestParam Map params, Model model) {
        try {
            return ajax(fruitService.queryDetailById(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     * 添加订单
     */
    @RequestMapping(value = "/order/creat_order", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object creatOrder(@RequestParam Map params, Model model) {
        try {
            return ajax(fruitService.createOrder(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     * 查询个人订单
     */
    @RequestMapping(value = "/order/query_order_by_id", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object queryOrderById(@RequestParam Map params, Model model) {
        try {
            return ajax(fruitService.queryOrderById(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     * 删除个人订单
     */
    @RequestMapping(value = "/order/del_order_by_id", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object delOrderById(@RequestParam Map params, Model model) {
        try {
            return ajax(fruitService.delOrderById(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }
}
