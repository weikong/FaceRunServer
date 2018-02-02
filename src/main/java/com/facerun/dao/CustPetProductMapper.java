package com.facerun.dao;

import com.facerun.bean.PetProduct;

import java.util.List;
import java.util.Map;

/**
 * 用户相关接口
 */
public interface CustPetProductMapper {

    /**
     * @return
     */
    List<PetProduct> getProductList(Map params);

}
