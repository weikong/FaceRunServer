package com.facerun.controller;


import com.facerun.bean.Record;
import com.facerun.dao.CustUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户相关操作
 */
@Controller
@RequestMapping("")
//@RestController
public class AccountController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CustUserMapper custUserMapper;

    /**
     * index
     */
    @GetMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 显示用户列表
     */
    @GetMapping("accounts")
    public String accounts(@RequestParam Map params, Model model) {
        List<Record> list = custUserMapper.getUsers();
        return list.toString();
    }
}
