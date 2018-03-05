package com.facerun.service;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.*;
import com.facerun.dao.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
public class FitService {
    private static final Logger log = LoggerFactory.getLogger(FitService.class);
    @Autowired
    private FitMapper fitMapper;
    @Autowired
    private FitPlanMapper fitPlanMapper;
    @Autowired
    private CustFitPlanMapper custFitPlanMapper;

    public List<Fit> queryFit(Map params) {
        List<Fit> list = fitMapper.selectByExample(null);
        return list;
    }

    public List<Object> querySysFitPlan(Map params) {
        List<Map> planList = custFitPlanMapper.getFitPlan(params);
        Map<String, List<Map>> namesMap = new LinkedHashMap<>();
        for (Map itemMap : planList) {
            String name = MapUtils.getString(itemMap, "name", "");
            if (StringUtils.isNotEmpty(name)) {
                if (!namesMap.containsKey(name)) {
                    List<Map> itemList = new ArrayList<>();
                    itemList.add(itemMap);
                    namesMap.put(name, itemList);
                } else {
                    List<Map> itemList = namesMap.get(name);
                    itemList.add(itemMap);
                }
            }
        }
        List<Object> list = new ArrayList<>();
        Iterator it = namesMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("planName", e.getKey());
            jsonObject.put("planList", e.getValue());
            list.add(jsonObject);
        }
        return list;
    }

    public List<FitPlan> queryFitPlan(Map params) {
        int accountid = MapUtils.getInteger(params, "accountId", -999);
        FitPlanExample example = new FitPlanExample();
        example.createCriteria().andAccountidEqualTo(accountid);
        List<FitPlan> list = fitPlanMapper.selectByExample(example);
        return list;
    }
}
