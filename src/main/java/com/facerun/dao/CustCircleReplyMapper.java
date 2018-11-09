package com.facerun.dao;

import com.facerun.bean.CircleReply;

import java.util.List;
import java.util.Map;

/**
 * 用户相关接口
 */
public interface CustCircleReplyMapper {

    /**
     * @return
     */
    List<CircleReply> getCircleReplyList(Map params);
}
