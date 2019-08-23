package com.facerun.service;

import com.facerun.bean.Account;
import com.facerun.bean.Circle;
import com.facerun.bean.CircleLike;
import com.facerun.bean.CircleLikeExample;
import com.facerun.dao.CircleLikeMapper;
import com.facerun.dao.CircleMapper;
import com.facerun.dao.CustCircleLikeMapper;
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
public class CircleLikeService {
    private static final Logger log = LoggerFactory.getLogger(CircleLikeService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CircleMapper circleMapper;
    @Autowired
    private CircleLikeMapper circleLikeMapper;
    @Autowired
    private CustCircleLikeMapper custCircleLikeMapper;

    public int queryCircleLikeCount(Map params) {
        int count = custCircleLikeMapper.getCircleLikeCount(params);
        return count;
    }

    public Object queryCircleLike(Map params) {
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int likeUserId = MapUtils.getInteger(params,"likeUserId",0);
        if (likeUserId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleLikeExample example = new CircleLikeExample();
        example.createCriteria().andLikeUserIdEqualTo(likeUserId);
        example.setOrderByClause("like_time desc");
        List<Circle> circleList = new ArrayList<>();
        List<CircleLike> list = circleLikeMapper.selectByExample(example);
        for (CircleLike circleLike : list){
            int circleId = circleLike.getLikeCircleId();
            Circle circle = circleMapper.selectByPrimaryKey(circleId);
            circleList.add(circle);
        }
        return circleList;
    }

    public boolean hasCircleLike(int userId,int circleId) {
        CircleLikeExample example = new CircleLikeExample();
        example.createCriteria().andLikeUserIdEqualTo(userId).andLikeCircleIdEqualTo(circleId);
        List<CircleLike> list = circleLikeMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    public void circleLikeInsert(Map params) {
        if (params == null){
            return;
        }
        int likeCircleId = MapUtils.getInteger(params,"likeCircleId",0);
        if (likeCircleId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        int likeUserId = MapUtils.getInteger(params,"likeUserId");
        Account account = accountService.accountSelect(likeUserId);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        String likeUserName = account.getName();
        CircleLike circleLike = new CircleLike();
        circleLike.setLikeCircleId(likeCircleId);
        circleLike.setLikeUserId(likeUserId);
        circleLike.setLikeUserName(likeUserName);
        int insert = circleLikeMapper.insertSelective(circleLike);
        if (insert != 1)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
    }

    public void circleLikeClear(Map params){
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int likeCircleId = MapUtils.getInteger(params,"likeCircleId",0);
        int likeUserId = MapUtils.getInteger(params,"likeUserId",0);
        if (likeCircleId <= 0 || likeUserId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleLikeExample example = new CircleLikeExample();
        example.createCriteria().andLikeCircleIdEqualTo(likeCircleId).andLikeUserIdEqualTo(likeUserId);
        int del = circleLikeMapper.deleteByExample(example);
    }
}
