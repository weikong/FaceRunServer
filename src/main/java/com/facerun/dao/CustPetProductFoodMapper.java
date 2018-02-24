package com.facerun.dao;

import com.facerun.bean.PetProduct;
import com.facerun.bean.PetProductFood;

import java.util.List;
import java.util.Map;

/**
 * ProductFood相关接口
 */
public interface CustPetProductFoodMapper {

    /**
     * @return
     */
    List<PetProductFood> getProductFoodList(Map params);

}
