package com.facerun.controller;


import com.facerun.bean.Account;
import com.facerun.bean.Record;
import com.facerun.bean.Run;
import com.facerun.bean.RunExample;
import com.facerun.dao.AccountMapper;
import com.facerun.dao.CustUserMapper;
import com.facerun.dao.RunMapper;
import com.facerun.exception.BizException;
import com.facerun.service.RunService;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("/run")
@RestController
public class RunController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(RunController.class);

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RunMapper runMapper;

    @Autowired
    private RunService runService;

    /**
     * 用户数据
     */
    @PostMapping("/list_by_account")
    @ResponseBody
    public Object runList(@RequestParam Map params, Model model) {
        try {
            String strAccount = MapUtils.getString(params,"Account","");
            List<Run> list = runService.runList(params);
            return ajax(list);
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
    public Object insert(HttpServletRequest request, @RequestParam Map params, Model model) {
        try {
            Run run = runService.runInsert(request,params);
            return ajax(run.getId());
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/insert_batch")
    @ResponseBody
    public Object insertBatch(@RequestParam Map params, Model model) {
        try {
            runService.runInsertBatch(params);
            return ajax();
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/del")
    @ResponseBody
    public Object runDelete(@RequestParam Map params, Model model) {
        try {
            runService.runDelete(params);
            return ajax();
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }
}
