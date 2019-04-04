package com.facerun.dao;

import com.facerun.bean.Circle;

import java.util.List;
import java.util.Map;

/**
 * 水果相关接口
 */
public interface CustFruitMapper {

    List<Map> queryFruitById(Map params);

    List<Map> queryFruitOrderById(Map params);

}
