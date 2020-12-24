package com.facerun.controller.YO;


import com.facerun.controller.AbsController;
import com.facerun.exception.BizException;
import com.facerun.service.YO.YOService;
import com.facerun.util.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("/yo")
@RestController
public class YOController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(YOController.class);

    @Autowired
    private YOService yoService;

    @GetMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Map params) {
        try {
            return ajax(yoService.queryAll(params));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @GetMapping("/queryByType")
    @ResponseBody
    public Object queryByType(@RequestParam Map params) {
        try {
            return ajax(yoService.queryByType(params));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }
}
