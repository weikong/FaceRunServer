package com.facerun.controller;


import com.facerun.bean.Run;
import com.facerun.dao.AccountMapper;
import com.facerun.dao.RunMapper;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.service.CircleService;
import com.facerun.service.RunService;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
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
@RequestMapping("/circle")
@RestController
public class CircleController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(CircleController.class);

    @Autowired
    private CircleService circleService;

    @PostMapping("/insert_reply")
    @ResponseBody
    public Object insertReply(@RequestParam Map params, Model model) {
        try {
            circleService.circleReplyInsert(params);
            return ajax();
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/query_reply")
    @ResponseBody
    public Object queryReply(@RequestParam Map params, Model model) {
        try {
            return ajax(circleService.circleReplyQuery(params));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Map params, Model model) {
        try {
            return ajax(circleService.circleQuery(params));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/querySearch")
    @ResponseBody
    public Object querySearch(@RequestParam Map params, Model model) {
        try {
            return ajax(circleService.circleSearchQuery(params));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/query_by_id")
    @ResponseBody
    public Object queryById(@RequestParam Map params, Model model) {
        try {
            return ajax(circleService.circleQueryById(params));
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
            circleService.circleInsert(params);
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
