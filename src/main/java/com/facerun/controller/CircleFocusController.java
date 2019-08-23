package com.facerun.controller;


import com.facerun.exception.BizException;
import com.facerun.service.CircleFocusService;
import com.facerun.util.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("/circle/focus")
@RestController
public class CircleFocusController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(CircleFocusController.class);

    @Autowired
    private CircleFocusService circleFocusService;

    @PostMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Map params, Model model) {
        try {
            return ajax(circleFocusService.circleFocusQuery(params));
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
            circleFocusService.circleFocusInsert(params);
            return ajax();
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }
    @PostMapping("/clear")
    @ResponseBody
    public Object clear(@RequestParam Map params, Model model) {
        try {
            circleFocusService.circleFocusClear(params);
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
