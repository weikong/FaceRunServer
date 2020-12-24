package com.facerun.dao;

import java.util.List;
import java.util.Map;

/**
 * 水果相关接口
 */
public interface CustRegionMapper {

    List<Map> queryRegionP(Map params);

    List<Map> queryRegionPC(Map params);

}
