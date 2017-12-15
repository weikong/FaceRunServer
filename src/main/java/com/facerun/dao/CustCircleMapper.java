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
     * @return
     */
    List<Circle> getCircleList(Map params);

}
