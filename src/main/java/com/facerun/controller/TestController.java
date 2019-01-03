package com.facerun.controller;


import com.facerun.bean.PetDating;
import com.facerun.bean.Run;
import com.facerun.dao.PetDatingMapper;
import com.facerun.dao.RunMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户相关操作
 */
@Controller
@RequestMapping("test")
public class TestController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RunMapper runMapper;
    @Autowired
    private PetDatingMapper petDatingMapper;

    /**
     * 用户数据
     */
    @GetMapping("welcome")
    public String welcome(@RequestParam Map params, Model model) {
        return "snack/welcome";
    }

    /**
     * 用户数据
     */
    @GetMapping("run_datas2")
    public String runDatas(@RequestParam Map params, Model model) {
        List<Run> list = runMapper.selectByExample(null);
        return "index";
    }

    /**
     * 丫圈首页
     */
    @GetMapping("food_main")
    public String foodMain(@RequestParam Map params, Model model) {
        return "Y/food_main";
    }

    /**
     * 丫圈菜品详情页
     */
    @GetMapping("food_detail")
    public String foodetail(@RequestParam Map params, Model model) {
        String id = MapUtils.getString(params,"id","");
        return "Y/food_detail";
    }

    /**
     * 宠物约会
     */
    @GetMapping("pet_dating")
    public String runDating(@RequestParam Map params, Model model) {
        List<PetDating> list = petDatingMapper.selectByExample(null);
        model.addAttribute("Dating", list);
        return "pet/pet_dating";
    }

    /**
     * 宠物约会
     */
    @RequestMapping("snack_wel")
    public String runSnackWel(@RequestParam Map params, Model model) {
        return "snack/welcome";
    }

    /**
     * 丫圈
     */
    @RequestMapping("y_quan")
    public String runYQuan(@RequestParam Map params, Model model) {
        return "Y/y_quan";
    }

    /**
     * 丫圈
     */
    @RequestMapping("frame_a")
    public String runFrameA(@RequestParam Map params, Model model) {
        return "Y/frame_a";
    }

    /**
     * 丫圈
     */
    @RequestMapping("frame_b")
    public String runFrameB(@RequestParam Map params, Model model) {
        return "Y/frame_b";
    }

    /**
     * 丫圈
     */
    @RequestMapping("frame_c")
    public String runFrameC(@RequestParam Map params, Model model) {
        return "Y/frame_c";
    }
}
