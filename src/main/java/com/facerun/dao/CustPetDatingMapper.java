package com.facerun.dao;

import com.facerun.bean.PetDating;
import com.facerun.bean.PetLookfor;

import java.util.List;
import java.util.Map;

/**
 * 寻犬相关接口
 */
public interface CustPetDatingMapper {

    /**
     * @return
     */
    List<PetDating> getDatingList(Map params);

}
