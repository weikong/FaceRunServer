package com.facerun.service.group;

import com.facerun.bean.Account;
import com.facerun.bean.ChatRecord;
import com.facerun.bean.GroupInfo;
import com.facerun.bean.GroupInfoExample;
import com.facerun.chat.SocketChatTest;
import com.facerun.dao.AccountMapper;
import com.facerun.dao.CustGroupInfoMapper;
import com.facerun.dao.GroupInfoMapper;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.util.BeanMapUtil;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class GroupService {
    private static final Logger log = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private SocketChatTest socketChatTest;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GroupInfoMapper groupInfoMapper;
    @Autowired
    private CustGroupInfoMapper custGroupInfoMapper;

    public Object groupCreate(Map params, HttpServletRequest request) {
        int ownerid = MapUtils.getInteger(params, "ownerid", 0);
        String groupname = MapUtils.getString(params, "groupname", "");
        String groupdesc = MapUtils.getString(params, "groupdesc", "");
        String groupheader = MapUtils.getString(params, "groupheader", "");
        String groupmembers = MapUtils.getString(params, "groupmembers", "");
        if (ownerid <= 0)
            throw new BizException(Code.USER_NOT_EXIST);
        Account account = accountService.accountSelect(ownerid);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        if (StringUtils.isEmpty(groupmembers))
            throw new BizException(Code.DATA_ERROR);
        String[] members = groupmembers.split(",");
        if (members.length == 0 || (members.length == 1 && members[0].equals("" + ownerid)))
            throw new BizException(Code.DATA_ERROR);
        if (StringUtils.isEmpty(groupname))
            groupname = "群聊";
        //判断是否包含创建者
        boolean hasOwner = false;
        for (String accountId : members) {
            if (accountId.equals("" + ownerid)) {
                hasOwner = true;
                break;
            }
        }
        if (!hasOwner) {
            groupmembers += "," + ownerid;
        }
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupaccount("G_" + UUID.randomUUID().toString());
        groupInfo.setGroupname(groupname);
        groupInfo.setGroupdesc(groupdesc);
        groupInfo.setGroupheader(groupheader);
        groupInfo.setGroupownerid(ownerid);
        groupInfo.setGroupmembers(groupmembers);
        int insert = groupInfoMapper.insert(groupInfo);
        if (insert != 1)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
        Map bean2Map = BeanMapUtil.getInstance().beanToMap(groupInfo);
        //同步发送群通知消息
        Map memberParams = new HashMap();
        memberParams.put("members", groupInfo.getGroupmembers().split(","));
        List<Account> list = custGroupInfoMapper.queryGroupMembersAccount(memberParams);
        bean2Map.put("members", list);
        ChatRecord content = socketChatTest.buildGroupTextNotice(groupInfo, account,account.getName() + " 创建群组 " + groupname);
        for (Account a : list) {
            socketChatTest.serviceSendMessage(a.getAccount(), content);
        }
        return bean2Map;
    }

    public Map groupQueryById(int groupid) {
        if (groupid <= 0)
            throw new BizException(Code.DATA_ERROR);
        GroupInfo groupInfo = groupInfoMapper.selectByPrimaryKey(groupid);
        Map bean2Map = BeanMapUtil.getInstance().beanToMap(groupInfo);
        Map memberParams = new HashMap();
        memberParams.put("members", groupInfo.getGroupmembers().split(","));
        List<Map> list = custGroupInfoMapper.queryGroupMembers(memberParams);
        bean2Map.put("members", list);
        return bean2Map;
    }

    public Map groupQueryById(Map params, HttpServletRequest request) {
        int groupid = MapUtils.getInteger(params, "groupid", 0);
        if (groupid <= 0)
            throw new BizException(Code.DATA_ERROR);
        GroupInfo groupInfo = groupInfoMapper.selectByPrimaryKey(groupid);
        Map bean2Map = BeanMapUtil.getInstance().beanToMap(groupInfo);
        Map memberParams = new HashMap();
        memberParams.put("members", groupInfo.getGroupmembers().split(","));
        List<Map> list = custGroupInfoMapper.queryGroupMembers(memberParams);
        bean2Map.put("members", list);
        return bean2Map;
    }

    public Object groupQueryByGroupAccount(Map params, HttpServletRequest request) {
        String groupaccount = MapUtils.getString(params, "groupaccount", "");
        if (StringUtils.isEmpty(groupaccount))
            throw new BizException(Code.DATA_ERROR);
        GroupInfoExample example = new GroupInfoExample();
        example.createCriteria().andGroupaccountEqualTo(groupaccount);
        List<GroupInfo> list = groupInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            GroupInfo groupInfo = list.get(0);
            Map bean2Map = BeanMapUtil.getInstance().beanToMap(groupInfo);
            Map memberParams = new HashMap();
            memberParams.put("members", groupInfo.getGroupmembers().split(","));
            List<Map> members = custGroupInfoMapper.queryGroupMembers(memberParams);
            bean2Map.put("members", members);
            return bean2Map;
        }
        return null;
    }

    public GroupInfo groupQueryByGroupAccount(String groupaccount) {
        if (StringUtils.isEmpty(groupaccount))
            throw new BizException(Code.DATA_ERROR);
        GroupInfoExample example = new GroupInfoExample();
        example.createCriteria().andGroupaccountEqualTo(groupaccount);
        List<GroupInfo> list = groupInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            GroupInfo groupInfo = list.get(0);
            return groupInfo;
        }
        return null;
    }

    public Object queryMyGroups(Map params, HttpServletRequest request) {
        int ownerid = MapUtils.getInteger(params, "ownerid", 0);
        GroupInfoExample example = new GroupInfoExample();
        example.createCriteria().andGroupowneridEqualTo(ownerid);
        example.setOrderByClause("createtime desc");
        List<GroupInfo> list = groupInfoMapper.selectByExample(example);
        return list;
    }

    public Object groupQueryByName(Map params, HttpServletRequest request) {
        String groupname = MapUtils.getString(params, "groupname", "");
        if (StringUtils.isEmpty(groupname))
            throw new BizException(Code.DATA_ERROR);
        GroupInfoExample example = new GroupInfoExample();
        example.createCriteria().andGroupnameLike(groupname);
        List<GroupInfo> list = groupInfoMapper.selectByExample(example);
        return list;
    }

    public Object groupUpdate(Map params, HttpServletRequest request) {
        int id = MapUtils.getInteger(params, "id", 0);
        String groupname = MapUtils.getString(params, "groupname", "");
        String groupdesc = MapUtils.getString(params, "groupdesc", "");
        String groupheader = MapUtils.getString(params, "groupheader", "");
        String updateUserName = MapUtils.getString(params, "updateusername", "");
        int userid = MapUtils.getInteger(params, "userid", 0);
        if (id <= 0)
            throw new BizException(Code.DATA_ERROR);
        if (userid <= 0)
            throw new BizException(Code.USER_NOT_EXIST);
        Account account = accountService.accountSelect(userid);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        if (StringUtils.isEmpty(groupname))
            throw new BizException(Code.DATA_ERROR);
        GroupInfo groupInfo = groupInfoMapper.selectByPrimaryKey(id);
        if (!StringUtils.isEmpty(groupname))
            groupInfo.setGroupname(groupname);
        if (!StringUtils.isEmpty(groupdesc))
            groupInfo.setGroupdesc(groupdesc);
        if (!StringUtils.isEmpty(groupheader))
            groupInfo.setGroupheader(groupheader);
        int update = groupInfoMapper.updateByPrimaryKeySelective(groupInfo);
        if (update == 0)
            throw new BizException(Code.FAIL_DATABASE_UPDATE);
        //同步发送群通知消息
        Map memberParams = new HashMap();
        memberParams.put("members", groupInfo.getGroupmembers().split(","));
        List<Account> list = custGroupInfoMapper.queryGroupMembersAccount(memberParams);
        ChatRecord content = socketChatTest.buildGroupTextNotice(groupInfo, account,updateUserName + " 更新群名称 " + groupname);
        for (Account a : list) {
            socketChatTest.serviceSendMessage(a.getAccount(), content);
        }
        return groupInfo;
    }
}
