package com.facerun.service;

import com.facerun.bean.Circle;
import com.facerun.bean.CircleCollection;
import com.facerun.bean.CircleCollectionExample;
import com.facerun.dao.CircleCollectionMapper;
import com.facerun.dao.CircleMapper;
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
public class CircleCollectionService {
    private static final Logger log = LoggerFactory.getLogger(CircleCollectionService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CircleMapper circleMapper;
    @Autowired
    private CircleCollectionMapper circleCollectionMapper;

    public void circleCollectionInsert(Map params){
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int circleId = MapUtils.getInteger(params,"circleId",0);
        int collectionUserId = MapUtils.getInteger(params,"collectionUserId",0);
        if (circleId <= 0 || collectionUserId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleCollection circleCollection = new CircleCollection();
        circleCollection.setCircleid(circleId);
        circleCollection.setCollectionuserid(collectionUserId);
        int insert = circleCollectionMapper.insert(circleCollection);
    }

    public void circleCollectionClear(Map params){
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int circleId = MapUtils.getInteger(params,"circleId",0);
        int collectionUserId = MapUtils.getInteger(params,"collectionUserId",0);
        if (circleId <= 0 || collectionUserId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleCollectionExample example = new CircleCollectionExample();
        example.createCriteria().andCircleidEqualTo(circleId).andCollectionuseridEqualTo(collectionUserId);
        int del = circleCollectionMapper.deleteByExample(example);
    }

    public Object circleCollectionQuery(Map params) {
        if (params == null){
            throw new BizException(Code.PARAMS_MISS);
        }
        int collectionUserId = MapUtils.getInteger(params,"collectionUserId",0);
        if (collectionUserId <= 0){
            throw new BizException(Code.PARAMS_MISS);
        }
        CircleCollectionExample example = new CircleCollectionExample();
        example.createCriteria().andCollectionuseridEqualTo(collectionUserId);
        example.setOrderByClause("collectiontime desc");
        List<CircleCollection> list = circleCollectionMapper.selectByExample(example);
        List<Circle> circleList = new ArrayList<>();
        for (CircleCollection circleCollection : list){
            int circleId = circleCollection.getCircleid();
            Circle circle = circleMapper.selectByPrimaryKey(circleId);
            circleList.add(circle);
        }
        return circleList;
    }
}
