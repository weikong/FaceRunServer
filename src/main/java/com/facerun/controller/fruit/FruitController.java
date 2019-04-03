package com.facerun.controller.fruit;


import com.facerun.bean.*;
import com.facerun.controller.AbsController;
import com.facerun.dao.*;
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
    private FruitMapper fruitMapper;
    @Autowired
    private CustFruitMapper custFruitMapper;
    @Autowired
    private FruitPriceTypeMapper fruitPriceTypeMapper;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询商品列表
     */
    @GetMapping("/list")
    @ResponseBody
    public Object fruitList(@RequestParam Map params, Model model) {
        FruitExample example = new FruitExample();
        List<Fruit> list = fruitMapper.selectByExample(example);
        return ajax(list);
    }

    /**
     * 通过商品id，查询商品详情
     */
    @GetMapping("/detail_by_id")
    @ResponseBody
    public Object fruitDetailById(@RequestParam Map params, Model model) {
        try {
            int id = MapUtils.getInteger(params, "id",-1);
            if (id <= 0)
                return ajax(Code.PARAMS_MISS);
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
            return ajax(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ajax(Code.FAIL);
    }
}
