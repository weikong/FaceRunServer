package com.facerun.controller;


import com.facerun.bean.Food;
import com.facerun.bean.FoodExample;
import com.facerun.dao.FoodMapper;
import com.facerun.dao.RunMapper;
import com.facerun.util.HttpUtil;
import com.facerun.util.translate.GoogleApi;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("translate")
@RestController
public class TranslateController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(TranslateController.class);

    /**
     * google翻译
     */
    @PostMapping("google_translate")
    @ResponseBody
    public String googleTranslate(@RequestParam Map params, Model model) {
        String content = MapUtils.getString(params, "content", "");
        String sourceLanguage = MapUtils.getString(params, "sourceLanguage", "");
        String targetLanguage = MapUtils.getString(params, "targetLanguage", "");
        String translateText = GoogleApi.translate(content, sourceLanguage, targetLanguage);
        return translateText;
    }
}
