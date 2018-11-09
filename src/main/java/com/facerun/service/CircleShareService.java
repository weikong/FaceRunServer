package com.facerun.service;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.Account;
import com.facerun.bean.CircleLike;
import com.facerun.bean.CircleShare;
import com.facerun.dao.CircleLikeMapper;
import com.facerun.dao.CircleShareMapper;
import com.facerun.dao.CustCircleLikeMapper;
import com.facerun.dao.CustCircleShareMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class CircleShareService {
    private static final Logger log = LoggerFactory.getLogger(CircleShareService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CircleShareMapper circleShareMapper;
    @Autowired
    private CustCircleShareMapper custCircleShareMapper;

    public int circleShareQuery(Map params) {
        int count = custCircleShareMapper.getCircleShareCount(params);
        return count;
    }

    public void circleShareInsert(Map params) {
        if (params == null){
            return;
        }
        int shareCircleId = MapUtils.getInteger(params,"shareCircleId",0);
        if (shareCircleId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        int shareUserId = MapUtils.getInteger(params,"shareUserId");
        Account account = accountService.accountSelect(shareUserId);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        String shareUserName = account.getName();
        CircleShare circleShare = new CircleShare();
        circleShare.setShareCircleId(shareCircleId);
        circleShare.setShareUserId(shareUserId);
        circleShare.setShareUserName(shareUserName);
        int insert = circleShareMapper.insertSelective(circleShare);
        if (insert != 1)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
    }

}
