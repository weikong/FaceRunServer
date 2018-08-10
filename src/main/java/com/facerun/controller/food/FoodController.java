package com.facerun.controller.food;


import com.facerun.bean.Account;
import com.facerun.bean.Record;
import com.facerun.controller.AbsController;
import com.facerun.dao.CustUserMapper;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("/food")
@RestController
public class FoodController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(FoodController.class);


    /**
     * 显示用户列表
     */
    @GetMapping("/list")
    @ResponseBody
    public Object foodList(@RequestParam Map params, Model model) {
        List<Record> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            Record record = new Record();
            record.put("name", "狂暴鸭脖（爆辣） " + i);
            record.put("desc", "月销量1254份 " + i);
            record.put("price", "3.5元/根");
            list.add(record);
        }
        return ajax(list);
    }
}
