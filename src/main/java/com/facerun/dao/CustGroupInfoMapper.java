package com.facerun.dao;

import com.facerun.bean.Account;

import java.util.List;
import java.util.Map;

/**
 * 水果相关接口
 */
public interface CustGroupInfoMapper {

    List<Map> queryGroupMembers(Map params);

    List<Account> queryGroupMembersAccount(Map params);
}
