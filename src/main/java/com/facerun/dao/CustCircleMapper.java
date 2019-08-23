package com.facerun.dao;

import com.facerun.bean.Circle;

import java.util.List;
import java.util.Map;

/**
 * 用户相关接口
 */
public interface CustCircleMapper {

    /**
     * @return
     */
    List<Circle> getCircleAllList(Map params);

    /**
     * @return 我的好友
     */
    List<Circle> getCircleList(Map params);

    /**
     * @return 查询跑友圈
     */
    List<Map> getCircleSearchList(Map params);

    /**
     * @return
     */
    List<Circle> getCircleByIdList(Map params);

}
