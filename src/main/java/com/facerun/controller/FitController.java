package com.facerun.controller;


import com.facerun.bean.Fit;
import com.facerun.exception.BizException;
import com.facerun.service.CircleService;
import com.facerun.service.FitService;
import com.facerun.util.Code;
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
@RequestMapping("/fit")
@RestController
public class FitController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(FitController.class);

    @Autowired
    private FitService fitService;

    @GetMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Map params, Model model) {
        try {
            List<Fit> list = fitService.queryFit(params);
            log.error("list.size() == " + list.size());
            return ajax(list);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/query_fit_plan")
    @ResponseBody
    public Object queryFitPlan(@RequestParam Map params, Model model) {
        try {
            List<Object> list = fitService.querySysFitPlan(params);
            return ajax(list);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }
}
