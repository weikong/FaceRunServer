package com.facerun.service;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.*;
import com.facerun.dao.*;
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
    @Autowired
    private CircleReplyMapper circleReplyMapper;
    @Autowired
    private CustCircleReplyMapper custCircleReplyMapper;

    public void circleReplyInsert(Map params) {
        if (params == null){
            return;
        }
        int replyCircleId = MapUtils.getInteger(params,"replyCircleId",0);
        if (replyCircleId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        int replyUserId = MapUtils.getInteger(params,"replyUserId");
        Account account = accountService.accountSelect(replyUserId);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        String replyUserName = account.getName();
        String replyContent = MapUtils.getString(params,"replyContent");
        int replyId = MapUtils.getInteger(params,"replyId",0);
        CircleReply circleReply = new CircleReply();
        circleReply.setReplyCircleId(replyCircleId);
        circleReply.setReplyUserId(replyUserId);
        circleReply.setReplyUserName(replyUserName);
        circleReply.setReplyContent(replyContent);
        circleReply.setReplyId(replyId);
        int insert = circleReplyMapper.insertSelective(circleReply);
    }

    public List<CircleReply> circleReplyQuery(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int reply_circle_id = MapUtils.getInteger(params,"reply_circle_id");
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        paramsWrapper.put("reply_circle_id", reply_circle_id);
        List<CircleReply> list = custCircleReplyMapper.getCircleReplyList(paramsWrapper);
        return list;
    }

    public List<Map> circleQuery(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<Map> list = custCircleMapper.getCircleList(paramsWrapper);
        for (Map map : list){
            int circle_id = (int) map.get("id");
            CircleReplyExample example = new CircleReplyExample();
            example.createCriteria().andReplyCircleIdEqualTo(circle_id);
            List<CircleReply> circleReplies = circleReplyMapper.selectByExample(example);
            map.put("reply",circleReplies);
        }
        return list;
    }

    public List<Map> circleSearchQuery(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<Map> list = custCircleMapper.getCircleSearchList(paramsWrapper);
        return list;
    }

    public List<Map> circleQueryById(Map params) {
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
        List<Map> list = custCircleMapper.getCircleByIdList(paramsWrapper);
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
