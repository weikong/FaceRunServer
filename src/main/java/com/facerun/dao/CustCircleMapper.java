package com.facerun.dao;

import com.facerun.bean.Circle;
import com.facerun.bean.Record;

import java.util.List;
import java.util.Map;

/**
 * 用户相关接口
 */
public interface CustCircleMapper {

    /**
     * @return 我的好友
     */
    List<Map> getCircleList(Map params);

    /**
     * @return 查询跑友圈
     */
    List<Map> getCircleSearchList(Map params);

    /**
     * @return
     */
    List<Map> getCircleByIdList(Map params);

}
