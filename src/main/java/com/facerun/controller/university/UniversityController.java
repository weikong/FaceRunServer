package com.facerun.controller.university;


import com.facerun.bean.University;
import com.facerun.controller.AbsController;
import com.facerun.service.university.UniversityService;
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
@RequestMapping("/university")
@RestController
public class UniversityController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(UniversityController.class);

    @Autowired
    private UniversityService universityService;

    /**
     * 查询大学信息
     */
    @GetMapping("queryUniversities")
    @ResponseBody
    public Object queryUniversities(@RequestParam Map params, Model model) {
        try {
            List<University> list = universityService.queryUniversities(params);
            return ajax(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "insertUniversity error";
    }

    /**
     * 查询大学信息
     */
    @GetMapping("queryUniversitiesByCity")
    @ResponseBody
    public Object queryUniversitiesByCity(@RequestParam Map params, Model model) {
        try {
            Object object = universityService.queryUniversitiesC(params);
            return ajax(object);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "queryUniversitiesByCity error";
    }

    /**
     * 查询大学信息
     */
    @GetMapping("queryUniversityByName")
    @ResponseBody
    public Object queryUniversityByName(@RequestParam Map params, Model model) {
        try {
            List<University> list = universityService.queryUniversityByName(params);
            return ajax(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "queryUniversityByName error";
    }

    /**
     * 查询大学信息
     */
    @GetMapping("queryUniversityByCity")
    @ResponseBody
    public Object queryUniversityByCity(@RequestParam Map params, Model model) {
        try {
            List<University> list = universityService.queryUniversityByName(params);
            return ajax(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "queryUniversityByCity error";
    }
}
