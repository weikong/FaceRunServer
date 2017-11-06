package com.facerun.dao;

import com.facerun.bean.Record;

import java.util.List;

/**
 * 用户相关接口
 */
public interface CustUserMapper {

    /**
     * @return
     */
    List<Record> getUsers();

}
