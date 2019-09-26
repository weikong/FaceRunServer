package com.facerun.dao;

import com.facerun.bean.ChatRecordAvatar;

import java.util.List;
import java.util.Map;

/**
 * 水果相关接口
 */
public interface CustChatRecordAckMapper {

    List<ChatRecordAvatar> queryChatRecordAck(Map params);

}
