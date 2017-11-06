package com.facerun.controller;


import com.facerun.bean.Record;
import com.facerun.bean.Run;
import com.facerun.dao.CustUserMapper;
import com.facerun.dao.RunMapper;
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
//@Controller
@RequestMapping("")
@RestController
public class RunController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(RunController.class);

    @Autowired
    private RunMapper runMapper;

    /**
     * 显示用户列表
     */
    @GetMapping("run_datas")
    public String runDatas(@RequestParam Map params, Model model) {
        List<Run> list = runMapper.selectByExample(null);
        return list.toString();
    }
}
