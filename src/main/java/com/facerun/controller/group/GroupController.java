package com.facerun.controller.group;


import com.facerun.controller.AbsController;
import com.facerun.dao.CustUserMapper;
import com.facerun.dao.GroupInfoMapper;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.service.group.GroupService;
import com.facerun.util.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("/group")
@RestController
public class GroupController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private CustUserMapper custUserMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GroupInfoMapper groupInfoMapper;
    @Autowired
    private GroupService groupService;

    /**
     * index
     */
    @GetMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("/groupCreate")
    @ResponseBody
    public Object insert(@RequestParam Map params, HttpServletRequest request) {
        try {
            return ajax(groupService.groupCreate(params, request));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/groupQueryById")
    @ResponseBody
    public Object query(@RequestParam Map params, HttpServletRequest request) {
        try {
            Object obj = groupService.groupQueryById(params, request);
            if (obj == null)
                throw new BizException(Code.DATA_ERROR);
            return ajax(obj);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/groupQueryByGroupAccount")
    @ResponseBody
    public Object queryByGroupAccount(@RequestParam Map params, HttpServletRequest request) {
        try {
            Object obj = groupService.groupQueryByGroupAccount(params, request);
            if (obj == null)
                throw new BizException(Code.DATA_ERROR);
            return ajax(obj);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/queryMyGroups")
    @ResponseBody
    public Object queryMyGroups(@RequestParam Map params, HttpServletRequest request) {
        try {
            Object obj = groupService.queryMyGroups(params, request);
            if (obj == null)
                throw new BizException(Code.DATA_ERROR);
            return ajax(obj);
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }

    @PostMapping("/groupUpdate")
    @ResponseBody
    public Object update(@RequestParam Map params, HttpServletRequest request) {
        try {
            return ajax(groupService.groupUpdate(params, request));
        } catch (BizException e) {
            log.error(e.getMessage());
            return ajax(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ajax(Code.FAIL);
        }
    }
}
