package com.facerun.controller;


import com.facerun.bean.Run;
import com.facerun.dao.RunMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("test_rest")
@RestController
public class TestRestController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);

    @Autowired
    private RunMapper runMapper;

    /**
     * 用户数据
     */
    @GetMapping("run_datas1")
    public String runDatas(@RequestParam Map params, Model model) {
        List<Run> list = runMapper.selectByExample(null);
        return list.toString();
    }
}
