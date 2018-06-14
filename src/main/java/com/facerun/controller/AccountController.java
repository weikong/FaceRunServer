package com.facerun.controller;


import com.facerun.bean.Account;
import com.facerun.bean.Record;
import com.facerun.dao.CustUserMapper;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
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
@RequestMapping("/account")
@RestController
public class AccountController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CustUserMapper custUserMapper;
    @Autowired
    private AccountService accountService;

    /**
     * index
     */
    @GetMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestParam Map params, Model model) {
        try {
            Account account = accountService.login(params);
            if (account == null)
                throw new BizException(Code.USER_NOT_EXIST);
            return ajax(account);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/logout")
    @ResponseBody
    public Object logOut(@RequestParam Map params, Model model) {
        try {
            Account account = accountService.logOut(params);
            if (account == null)
                throw new BizException(Code.USER_NOT_EXIST);
            return ajax(account);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/insert")
    @ResponseBody
    public Object insert(@RequestParam Map params, Model model) {
        try {
            accountService.accountInsert(params);
            return ajax();
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public Object registerUser(@RequestParam Map params, Model model) {
        try {
            return ajax(accountService.registerUser(params));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    /**
     * 查询用户
     */
    @GetMapping("/select_by_id")
    @ResponseBody
    public Object accountById(@RequestParam Map params, Model model) {
        try {
            Account account = accountService.accountSelect(params);
            if (account == null)
                throw new BizException(Code.USER_NOT_EXIST);
            return ajax(account);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    /**
     * 查询用户
     */
    @GetMapping("/select_by_account")
    @ResponseBody
    public Object accountByAccount(@RequestParam Map params, Model model) {
        try {
            String strAccount = MapUtils.getString(params,"account","");
            Account account = accountService.accountSelect(strAccount);
            if (account == null)
                throw new BizException(Code.USER_NOT_EXIST);
            return ajax(account);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    /**
     * 显示用户列表
     */
    @GetMapping("/list")
    @ResponseBody
    public Object accountList(@RequestParam Map params, Model model) {
        List<Record> list = custUserMapper.getUsers();
        return ajax(list);
    }
}
