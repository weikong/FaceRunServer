package com.facerun.controller.food;


import com.facerun.bean.Food;
import com.facerun.bean.FoodExample;
import com.facerun.controller.AbsController;
import com.facerun.dao.FoodMapper;
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
@RequestMapping("/food")
@RestController
public class FoodController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    private FoodMapper foodMapper;

    /**
     * 查询丫圈列表数据
     */
    @GetMapping("/list")
    @ResponseBody
    public Object foodList(@RequestParam Map params, Model model) {
        String kind = MapUtils.getString(params, "kind", "");
        FoodExample example = new FoodExample();
        if (StringUtils.isNotEmpty(kind))
            example.createCriteria().andKindEqualTo(kind);
        List<Food> list = foodMapper.selectByExample(example);
        return ajax(list);
    }

    /**
     * 查询丫圈卤菜详情
     */
    @GetMapping("/detail")
    @ResponseBody
    public Object foodDetail(@RequestParam Map params, Model model) {
        int id = MapUtils.getInteger(params, "id");
        FoodExample example = new FoodExample();
        if (id > 0)
            example.createCriteria().andIdEqualTo(id);
        Food food = foodMapper.selectByPrimaryKey(id);
        return ajax(food);
    }
}
