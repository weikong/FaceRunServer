package com.facerun.service;

import com.facerun.bean.*;
import com.facerun.dao.CircleFocusMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class CircleFocusService {
    private static final Logger log = LoggerFactory.getLogger(CircleFocusService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CircleFocusMapper circleFocusMapper;

    public void circleFocusInsert(Map params){
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int operatorid = MapUtils.getInteger(params,"operatorId",0);
        int focuseduserid = MapUtils.getInteger(params,"focusedUserId",0);
        if (operatorid <= 0 || focuseduserid <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleFocus circleFocus = new CircleFocus();
        circleFocus.setOperatorid(operatorid);
        circleFocus.setFocuseduserid(focuseduserid);
        circleFocusMapper.insert(circleFocus);
    }

    public void circleFocusClear(Map params){
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int operatorid = MapUtils.getInteger(params,"operatorId",0);
        int focuseduserid = MapUtils.getInteger(params,"focusedUserId",0);
        if (operatorid <= 0 || focuseduserid <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleFocusExample example = new CircleFocusExample();
        example.createCriteria().andOperatoridEqualTo(operatorid).andFocuseduseridEqualTo(focuseduserid);
        int del = circleFocusMapper.deleteByExample(example);
    }

    public boolean hasCircleFocus(int operatorid,int focuseduserid) {
        CircleFocusExample example = new CircleFocusExample();
        example.createCriteria().andOperatoridEqualTo(operatorid).andFocuseduseridEqualTo(focuseduserid);
        List<CircleFocus> list = circleFocusMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    public Object circleFocusQuery(Map params) {
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int userId = MapUtils.getInteger(params,"userId",0);
        if (userId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleFocusExample example = new CircleFocusExample();
        example.createCriteria().andOperatoridEqualTo(userId);
        example.setOrderByClause("focustime desc");
        List<CircleFocus> list = circleFocusMapper.selectByExample(example);
        List<Account> accountList = new ArrayList<>();
        for (CircleFocus circleFocus : list){
            int accountId = circleFocus.getFocuseduserid();
            Account account = accountService.accountSelect(accountId);
            accountList.add(account);
        }
        return accountList;
    }
}
