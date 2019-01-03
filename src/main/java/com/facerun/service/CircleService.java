package com.facerun.service;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.*;
import com.facerun.dao.*;
import com.facerun.exception.BizException;
import com.facerun.util.BeanMapUtil;
import com.facerun.util.Code;
import com.facerun.util.Config;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private CircleLikeService circleLikeService;
    @Autowired
    private CircleShareService circleShareService;

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
        int replyRootId = MapUtils.getInteger(params,"replyRootId",0);
        CircleReply circleReply = new CircleReply();
        circleReply.setReplyCircleId(replyCircleId);
        circleReply.setReplyUserId(replyUserId);
        circleReply.setReplyUserName(replyUserName);
        circleReply.setReplyContent(replyContent);
        circleReply.setReplyId(replyId);
        circleReply.setReplyRootId(replyRootId);
        int insert = circleReplyMapper.insertSelective(circleReply);
        if (insert <= 0)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
    }

    public List<CircleReply> circleReplyQuery(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int reply_circle_id = MapUtils.getInteger(params,"reply_circle_id");
        int reply_root_id = MapUtils.getInteger(params,"reply_root_id",0);
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        paramsWrapper.put("reply_circle_id", reply_circle_id);
        paramsWrapper.put("reply_root_id", reply_root_id);
        List<CircleReply> list = custCircleReplyMapper.getCircleReplyList(paramsWrapper);
        List<CircleReply> responseList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            CircleReply circleReply = list.get(i);
            responseList.add(circleReply);
            paramsWrapper.put("reply_root_id", circleReply.getId());
            List<CircleReply> items = circleReplyItemsQuery(paramsWrapper);
            if (items != null && items.size() > 0)
                responseList.addAll(items);
        }
        return responseList;
    }

    public List<CircleReply> circleReplyItemsQuery(Map params) {
        List<CircleReply> responseList = new ArrayList<>();
        int reply_root_id = MapUtils.getInteger(params,"reply_root_id",0);
        if (reply_root_id <= 0)
            return responseList;
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int reply_circle_id = MapUtils.getInteger(params,"reply_circle_id");
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        paramsWrapper.put("reply_circle_id", reply_circle_id);
        paramsWrapper.put("reply_root_id", reply_root_id);
        List<CircleReply> list = custCircleReplyMapper.getCircleReplyList(paramsWrapper);
        return list;
    }

    public List<Circle> circleQuery(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<Circle> list = custCircleMapper.getCircleList(paramsWrapper);
        for (Circle circle : list){
            Map map = BeanMapUtil.beanToMap(circle);
            int circle_id = (int) map.get("id");
            Map paramReply = new HashMap();
            paramReply.put("pageNum", 1);
            paramReply.put("pageSize", 4);
            paramReply.put("reply_circle_id", circle_id);
            paramReply.put("reply_root_id", 0);
            List<CircleReply> circleReplies = circleReplyQuery(paramReply);
            map.put("reply",circleReplies);
            map.put("replyCount",circleReplies == null ? 0 : circleReplies.size());
            paramReply.put("like_circle_id", circle_id);
            int likeCount = circleLikeService.circleLikeQuery(paramReply);
            int userId = MapUtils.getInteger(params,"userId",0);
            boolean hasThisUser = false;
            if (userId>0){
                hasThisUser = circleLikeService.hasCircleLike(userId,circle_id);
            }
            map.put("hasThisUser",hasThisUser);
            paramReply.put("share_circle_id", circle_id);
            int shareCount = circleShareService.circleShareQuery(paramReply);
            map.put("likeCount",likeCount);
            map.put("shareCount",shareCount);
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
