package com.facerun.controller.pet;


import com.facerun.controller.AbsController;
import com.facerun.exception.BizException;
import com.facerun.service.pet.PetCategaryService;
import com.facerun.service.pet.PetProductService;
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
@RequestMapping("/pet_categary")
@RestController
public class PetCategaryController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(PetCategaryController.class);

    @Autowired
    private PetCategaryService petCategaryService;

    @PostMapping("/query")
    @ResponseBody
    public Object query(@RequestParam Map params, Model model) {
        try {
            return ajax(petCategaryService.queryAll());
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }
}
