package com.facerun.service;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.*;
import com.facerun.dao.AccountMapper;
import com.facerun.dao.CircleMapper;
import com.facerun.dao.CustCircleMapper;
import com.facerun.dao.RunMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import com.facerun.util.Config;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class CircleService {
    private static final Logger log = LoggerFactory.getLogger(CircleService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CircleMapper circleMapper;
    @Autowired
    private CustCircleMapper custCircleMapper;

    public List<Circle> circleQuery(Map params) {
//        int account_id = MapUtils.getInteger(params, "account_id", -999);
//        Account account = accountService.accountSelect(account_id);
//        if (account == null)
//            throw new BizException(Code.USER_NOT_EXIST);
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<Circle> list = custCircleMapper.getCircleList(paramsWrapper);
        return list;
    }

    public List<Circle> circleQueryById(Map params) {
        int account_id = MapUtils.getInteger(params, "account_id", -999);
        Account account = accountService.accountSelect(account_id);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        paramsWrapper.put("account_id", account_id);
        List<Circle> list = custCircleMapper.getCircleByIdList(paramsWrapper);
        return list;
    }

    public void circleInsert(Map params) {
        int account_id = MapUtils.getInteger(params, "account_id", 0);
        Account account = accountService.accountSelect(account_id);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        String data = MapUtils.getString(params, "data", "");
        Circle circle = JSONObject.parseObject(data, Circle.class);
        int insert = circleMapper.insertSelective(circle);
        if (insert != 1)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
    }
}
