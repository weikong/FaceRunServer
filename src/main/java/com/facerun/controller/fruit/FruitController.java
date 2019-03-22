package com.facerun.controller.fruit;


import com.facerun.bean.Food;
import com.facerun.bean.FoodExample;
import com.facerun.bean.Fruit;
import com.facerun.bean.FruitExample;
import com.facerun.controller.AbsController;
import com.facerun.dao.FoodMapper;
import com.facerun.dao.FruitMapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询丫圈列表数据
     */
    @GetMapping("/list")
    @ResponseBody
    public Object foodList(@RequestParam Map params, Model model) {
        FruitExample example = new FruitExample();
        List<Fruit> list = fruitMapper.selectByExample(example);
        return ajax(list);
    }

    /**
     * 查询丫圈卤菜详情
     */
    @GetMapping("/detail")
    @ResponseBody
    public Object foodDetail(@RequestParam Map params, Model model) {
        int id = MapUtils.getInteger(params, "id");
        FruitExample example = new FruitExample();
        if (id > 0)
            example.createCriteria().andIdEqualTo(id);
        Fruit fruit = fruitMapper.selectByPrimaryKey(id);
        return ajax(fruit);
    }
}
