package com.facerun.service;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.Account;
import com.facerun.bean.Circle;
import com.facerun.bean.Fit;
import com.facerun.dao.CircleMapper;
import com.facerun.dao.CustCircleMapper;
import com.facerun.dao.FitMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class FitService {
    private static final Logger log = LoggerFactory.getLogger(FitService.class);
    @Autowired
    private FitMapper fitMapper;

    public List<Fit> queryFit(Map params) {
        List<Fit> list = fitMapper.selectByExample(null);
        return list;
    }
}
