package com.facerun.controller;


import com.facerun.exception.BizException;
import com.facerun.service.CircleCollectionService;
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
@RequestMapping("/circle/collection")
@RestController
public class CircleCollectionController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(CircleCollectionController.class);

    @Autowired
    private CircleCollectionService circleCollectionService;

    @PostMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Map params, Model model) {
        try {
            return ajax(circleCollectionService.circleCollectionQuery(params));
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
            circleCollectionService.circleCollectionInsert(params);
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
            circleCollectionService.circleCollectionClear(params);
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
