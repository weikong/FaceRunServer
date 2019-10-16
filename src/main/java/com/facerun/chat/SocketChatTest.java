package com.facerun.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.*;
import com.facerun.dao.*;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by maesinfo on 2019/9/17.
 */
@Service
@Transactional
public class SocketChatTest {

    @Autowired
    private ChatRecordMapper chatRecordMapper;
    @Autowired
    private ChatAckMapper chatAckMapper;
    @Autowired
    private CustChatRecordAckMapper custChatRecordAckMapper;
    @Autowired
    private AccountService accountService;

    @Autowired
    private GroupInfoMapper groupInfoMapper;
    @Autowired
    private CustGroupInfoMapper custGroupInfoMapper;

    private static final int PORT = 9999;
    private Map<String, PrintWriter> printWriterMap = new HashMap<>();
    private Map<String, Service> serviceMap = new HashMap<>();
    private Map<String, String> IPAddressIDMap = new HashMap<>();

    private ServerSocket server = null;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;

//    public static void main(String[] args) {
//        new SocketChatTest();
//    }

    public void SocketChatTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //步骤一
                    server = new ServerSocket(PORT);
                    mExecutorService = Executors.newCachedThreadPool();
                    System.out.println("服务器已启动...");
                    Socket client = null;
                    while (true) {
                        //步骤二，每接受到一个新Socket连接请求，就会新建一个Thread去处理与其之间的通信
                        try {
                            client = server.accept();
                            client.setSoTimeout(35 * 1000);
                            String getHostAddress = client.getInetAddress().getHostAddress();
                            if (serviceMap.containsKey(getHostAddress)) {
                                Service service = serviceMap.get(getHostAddress);
                                service.setSocket(client);
                            } else {
                                Service service = new Service(client);
                                serviceMap.put(getHostAddress, service);
                                mExecutorService.execute(service);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("服务停止");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("服务停止1");
                }
                System.out.println("服务停止2");
            }
        }).start();
    }

    class Service implements Runnable {
        private String serviceId;
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter printWriter = null;
        private boolean setStop = false;

        public Service(Socket socket) {
            //这段代码对应步骤三
            this.socket = socket;
            String getHostAddress = socket.getInetAddress().getHostAddress();
            serviceId = UUID.randomUUID().toString();
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                printWriter.println(connectSuccess());
                System.out.println(connectSuccess());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Service IOException");
            }
        }

        public void setSocket(Socket socket) {
            //这段代码对应步骤三
            this.socket = socket;
            String getHostAddress = socket.getInetAddress().getHostAddress();
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                printWriter.println(connectSuccess());
                System.out.println(connectSuccess());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Service IOException");
            }
        }

        public String getServiceId() {
            return serviceId;
        }

        public void stopService() {
            setStop = true;
        }

        @Override
        public synchronized void run() {
            try {
                while (!setStop) {
                    System.out.println("setStop:" + setStop + "  isConnected = " + socket.isConnected());
                    //循环接收、读取 Client 端发送过来的信息
//                    if (in != null && (receiveMsg = in.readLine()) != null) {
                    if (in != null && (receiveMsg = in.readLine()) != null) {
                        if (StringUtils.isEmpty(receiveMsg) || receiveMsg == null) {
                            System.out.println("receiveMsg:" + receiveMsg + "  getPort = " + socket.getPort());
                            return;
                        }
                        ChatRecordAvatar chatRecord = JSONObject.parseObject(receiveMsg, ChatRecordAvatar.class);
                        System.out.println("receiveMsg:2 " + JSON.toJSONString(chatRecord));
                        /**
                         * type 9：聊天内容； 1：Ping； 2：Connect；3：Disconnect； 4：Login
                         * */
                        int type = chatRecord.getMessagetype();
                        switch (type) {
                            case 1: //Ping
                                System.out.println("receiveMsg:Ping");
                                printWriter.println(JSON.toJSONString(chatRecord));
                                break;
                            case 2: //Connect
                                System.out.println("receiveMsg:Connect");
                                break;
                            case 3: //Disconnect
                                System.out.println(disconnect());
                                printWriter.println(disconnect());
                                String hd = socket.getInetAddress().getHostAddress();
                                String uid = chatRecord.getMessagefromid();
                                if (printWriterMap.containsKey(uid)) {
                                    printWriterMap.remove(uid);
                                }
                                if (serviceMap.containsKey(hd)) {
                                    serviceMap.get(hd).stopService();
                                    serviceMap.remove(hd);
                                }

                                in.close();
                                in = null;
                                //接受 Client 端的断开连接请求，并关闭 Socket 连接
                                socket.close();
                                socket = null;
                                break;
                            case 4: //Login
                                String getHostAddress = socket.getInetAddress().getHostAddress();
                                String userId = chatRecord.getMessagefromid();

                                //更新Service
                                if (IPAddressIDMap.containsKey(userId)) {
                                    String ip = IPAddressIDMap.get(userId);
                                    if (serviceMap.containsKey(ip)) {
                                        Service service = serviceMap.get(ip);
                                        System.out.println("service.serviceId: " + service.getServiceId());
                                        if (!service.getServiceId().equals(serviceId)) {
                                            service.stopService();
                                            serviceMap.remove(ip);
                                        }
                                    }
                                }
                                IPAddressIDMap.put(userId, getHostAddress);

                                //清除之前的PrintWriter
                                if (printWriterMap.containsKey(userId)) {
                                    printWriterMap.get(userId).println(disconnect());
                                    System.out.println(disconnect());
                                }
                                //更新PrintWriter
                                printWriterMap.put(userId, printWriter);
                                printWriter.println(JSON.toJSONString(chatRecord));
                                String offlineData = buildOfflineData(userId);
                                if (!StringUtils.isEmpty(offlineData))
                                    printWriter.println(offlineData);
                                break;
                            case 5:
                                try {
                                    //更新ChatAck
                                    ChatAckExample example = new ChatAckExample();
                                    example.createCriteria().andMessageidEqualTo(chatRecord.getMessageid()).andMessagetoidEqualTo(chatRecord.getSourcesenderid());
                                    List<ChatAck> list = chatAckMapper.selectByExample(example);
                                    if (list != null && list.size() > 0) {
                                        for (ChatAck chatAck : list){
                                            chatAck.setMessageack(1);
                                            chatAckMapper.updateByPrimaryKey(chatAck);
                                        }
                                    }
                                    System.out.println(receiveMsg);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 9: //聊天内容
                                //向 Client 端反馈、发送信息
//                                printWriter.println(JSON.toJSONString(ackClient(chatRecord.getMessageid())));
                                int insert = 0;
//                                String content = "";
                                boolean isGroup = chatRecord.getGroupdata() == 1 ? true: false;
                                if (!isGroup){
                                    Account accountFrom = accountService.accountSelect(chatRecord.getSourcesenderid());
                                    Account accountTo = accountService.accountSelect(chatRecord.getMessagetoid());
                                    try {
                                        chatRecord.setMessagetime(new Date());
                                        chatRecord.setMessagestate(1);
                                        if (accountFrom != null) {
                                            chatRecord.setMessagefromavatar(accountFrom.getHeadPortrait());
                                        }
                                        if (accountTo != null) {
                                            chatRecord.setMessagetoavatar(accountTo.getHeadPortrait());
                                        }
                                        //存入emoji表情错误，先进行编码，取出的时候进行解码
                                        String content = chatRecord.getMessagecontent();
                                        content = URLEncoder.encode(content);
                                        chatRecord.setMessagecontent(content);
                                        ChatRecordExample example = new ChatRecordExample();
                                        example.createCriteria().andMessageidEqualTo(chatRecord.getMessageid());
                                        List<ChatRecord> list = chatRecordMapper.selectByExample(example);
                                        if (list != null && list.size() > 0){
                                            insert = chatRecordMapper.updateByPrimaryKeySelective(list.get(0));
                                        } else {
                                            insert = chatRecordMapper.insert(chatRecord);
                                        }
                                        content = URLDecoder.decode(content);
                                        chatRecord.setMessagecontent(content);
                                        if (insert == 0)
                                            return;
                                        String from = chatRecord.getSourcesenderid();
                                        String to = chatRecord.getMessagetoid();

                                        ChatAckExample exampleAck = new ChatAckExample();
                                        exampleAck.createCriteria().andMessageidEqualTo(chatRecord.getMessageid()).andMessagetoidEqualTo(to);
                                        List<ChatAck> listAck = chatAckMapper.selectByExample(exampleAck);
                                        if (listAck == null || listAck.size() == 0){
                                            ChatAck chatAck = new ChatAck();
                                            chatAck.setMessageid(chatRecord.getMessageid());
                                            chatAck.setMessagetoid(to);
                                            chatAck.setMessagetime(chatRecord.getMessagetime());
                                            if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to) && from.equals(to)){
                                                chatAck.setMessageack(1);
                                            } else {
                                                chatAck.setMessageack(0);
                                            }
                                            int insertAck = chatAckMapper.insert(chatAck);
                                        }
                                        if (!StringUtils.isEmpty(to) && printWriterMap.containsKey(to)) {
                                            PrintWriter pw = printWriterMap.get(to);
                                            if (pw != null && pw != printWriter) {
//                                                chatRecord.setMessagestate(1);
                                                pw.println(JSON.toJSONString(chatRecord));
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        chatRecord.setMessagetype(5);
                                        if (insert == 0) {
                                            chatRecord.setMessagestate(9);
                                            chatRecordMapper.updateByPrimaryKey(chatRecord);
                                        }
                                        printWriter.println(JSON.toJSONString(chatRecord));
                                    }
                                } else {
//                                    Account accountFrom = accountService.accountSelect(chatRecord.getSourcesenderid());
                                    chatRecord.setMessagetime(new Date());
                                    chatRecord.setMessagestate(1);
//                                    if (accountFrom != null) {
//                                        chatRecord.setMessagefromavatar(accountFrom.getHeadPortrait());
//                                    }
                                    chatRecord.setGroupdata(1);
                                    chatRecord.setMessagefromavatar("");
                                    if (StringUtils.isEmpty(chatRecord.getMessagefromid()))
                                        throw new BizException(Code.DATA_ERROR);
                                    GroupInfoExample exampleG = new GroupInfoExample();
                                    exampleG.createCriteria().andGroupaccountEqualTo(chatRecord.getMessagefromid());
                                    List<GroupInfo> groupInfoList = groupInfoMapper.selectByExample(exampleG);
                                    if (groupInfoList.size() > 0) {
                                        GroupInfo groupInfo = groupInfoList.get(0);
                                        Map memberParams = new HashMap();
                                        memberParams.put("members", groupInfo.getGroupmembers().split(","));
                                        List<Account> members = custGroupInfoMapper.queryGroupMembersAccount(memberParams);
                                        for (Account account : members){
                                            try {
                                                chatRecord.setMessagetoid(account.getAccount());
                                                chatRecord.setMessagetoname(account.getName());
                                                chatRecord.setMessagetoavatar(account.getHeadPortrait());
                                                //存入emoji表情错误，先进行编码，取出的时候进行解码
                                                String content = chatRecord.getMessagecontent();
                                                content = URLEncoder.encode(content);
                                                chatRecord.setMessagecontent(content);
                                                ChatRecordExample example = new ChatRecordExample();
                                                example.createCriteria().andMessageidEqualTo(chatRecord.getMessageid()).andMessagetoidEqualTo(account.getAccount());
                                                List<ChatRecord> list = chatRecordMapper.selectByExample(example);
                                                if (list != null && list.size() > 0){
                                                    insert = chatRecordMapper.updateByPrimaryKeySelective(list.get(0));
                                                } else {
                                                    insert = chatRecordMapper.insert(chatRecord);
                                                }
                                                content = URLDecoder.decode(content);
                                                chatRecord.setMessagecontent(content);
                                                if (insert == 0)
                                                    return;
                                                String from = chatRecord.getSourcesenderid();
                                                String to = account.getAccount();
                                                ChatAckExample exampleAck = new ChatAckExample();
                                                exampleAck.createCriteria().andMessageidEqualTo(chatRecord.getMessageid()).andMessagetoidEqualTo(to);
                                                List<ChatAck> listAck = chatAckMapper.selectByExample(exampleAck);
                                                if (listAck == null || listAck.size() == 0){
                                                    ChatAck chatAck = new ChatAck();
                                                    chatAck.setMessageid(chatRecord.getMessageid());
                                                    chatAck.setMessagetoid(to);
                                                    chatAck.setMessagetime(chatRecord.getMessagetime());
                                                    if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to) && from.equals(to)){
                                                        chatAck.setMessageack(1);
                                                    } else {
                                                        chatAck.setMessageack(0);
                                                    }
                                                    int insertAck = chatAckMapper.insert(chatAck);
                                                }
                                                if (!StringUtils.isEmpty(to) && printWriterMap.containsKey(to)) {
                                                    PrintWriter pw = printWriterMap.get(to);
                                                    if (pw != null && pw != printWriter) {
//                                                        chatRecord.setMessagestate(1);
                                                        pw.println(JSON.toJSONString(chatRecord));
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            } finally {

                                            }
                                        }
                                        chatRecord.setMessagetype(5);
                                        if (insert == 0) {
                                            chatRecord.setMessagestate(9);
                                            chatRecordMapper.updateByPrimaryKey(chatRecord);
                                        }
                                        printWriter.println(JSON.toJSONString(chatRecord));
                                    }
                                }
                                break;
                        }
                    } else {
                        String getHostAddress = socket.getInetAddress().getHostAddress();
                        if (!StringUtils.isEmpty(getHostAddress) && serviceMap.containsKey(getHostAddress)) {
                            serviceMap.remove(getHostAddress);
                        }
                        Iterator<Map.Entry<String, String>> entries = IPAddressIDMap.entrySet().iterator();
                        String IpAddressKey = "";
                        while (entries.hasNext()) {
                            Map.Entry<String, String> entry = entries.next();
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (getHostAddress.equals(value)) {
                                IpAddressKey = key;
                                break;
                            }
                        }
                        if (!StringUtils.isEmpty(IpAddressKey)) {
                            IPAddressIDMap.remove(IpAddressKey);
                            printWriterMap.remove(IpAddressKey);
                        }
                        stopService();
                        in.close();
                        in = null;
                        //接受 Client 端的断开连接请求，并关闭 Socket 连接
                        socket.close();
                        socket = null;
                    }
                }
                System.out.println("服务停止3");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("服务停止4");
                String getHostAddress = socket.getInetAddress().getHostAddress();
                if (!StringUtils.isEmpty(getHostAddress) && serviceMap.containsKey(getHostAddress)) {
                    serviceMap.remove(getHostAddress);
                }
                Iterator<Map.Entry<String, String>> entries = IPAddressIDMap.entrySet().iterator();
                String IpAddressKey = "";
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (getHostAddress.equals(value)) {
                        IpAddressKey = key;
                        break;
                    }
                }
                if (!StringUtils.isEmpty(IpAddressKey)) {
                    IPAddressIDMap.remove(IpAddressKey);
                    printWriterMap.remove(IpAddressKey);
                }
                stopService();
                try {
                    in.close();
                    in = null;
                    //接受 Client 端的断开连接请求，并关闭 Socket 连接
                    socket.close();
                    socket = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public String connectSuccess() {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setMessagecontent("成功连接服务器");
        chatRecord.setMessagetype(2);
        chatRecord.setMessageid(UUID.randomUUID().toString());
        return JSON.toJSONString(chatRecord);
    }

    public String disconnect() {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setMessagecontent("客户端请求断开连接");
        chatRecord.setMessagetype(3);
        chatRecord.setMessageid(UUID.randomUUID().toString());
        return JSON.toJSONString(chatRecord);
    }

    public String ackClient(String messageId) {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setMessagecontent("ACK");
        chatRecord.setMessagetype(5);
        chatRecord.setMessageid(messageId);
        return JSON.toJSONString(chatRecord);
    }

    public String buildOfflineData(String toId) {
        List<ChatRecord> list = new ArrayList<>();
        Map params = new HashMap();
        params.put("toid", toId);
        List<ChatRecordAvatar> chatRecordList = custChatRecordAckMapper.queryChatRecordAck(params);
        if (chatRecordList == null || chatRecordList.size() == 0)
            return "";
        for (ChatRecordAvatar chatRecord : chatRecordList) {
            chatRecord.setMessagecontent(URLDecoder.decode(chatRecord.getMessagecontent()));
        }
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setMessagecontent("离线数据");
        chatRecord.setMessagetype(6);
        chatRecord.setMessageid(UUID.randomUUID().toString());
        chatRecord.setMessagecontent(JSON.toJSONString(chatRecordList));
        return JSON.toJSONString(chatRecord);
    }

    /**
     * 群通知消息
     * */
    public ChatRecord buildGroupTextNotice(String groupAccount,String groupName,String message) {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setSourcesenderid("-1");
        chatRecord.setSourcesendername("SYSTEM");
        chatRecord.setMessagefromid(groupAccount);
        chatRecord.setMessagefromname(groupName);
        chatRecord.setMessagecontent(message);
        chatRecord.setMessagetype(7);
        chatRecord.setMessagechattype(1);
        chatRecord.setMessagestate(1);
        chatRecord.setMessageid(UUID.randomUUID().toString());
        chatRecord.setMessagetime(new Date());
        chatRecord.setGroupdata(1);
        return chatRecord;
    }

    public void serviceSendMessage(String userId,ChatRecord chatRecord){
        chatRecord.setMessagetoid(userId);
        //插入聊天列表
        int insert = chatRecordMapper.insert(chatRecord);
        if (printWriterMap != null && printWriterMap.containsKey(userId)){
            printWriterMap.get(userId).println(JSON.toJSONString(chatRecord));
        }
    }

//    public void receiveMessage(String groupAccount,ChatRecord chatRecord){
//        if (StringUtils.isEmpty(groupAccount))
//            throw new BizException(Code.DATA_ERROR);
//        GroupInfoExample example = new GroupInfoExample();
//        example.createCriteria().andGroupaccountEqualTo(groupAccount);
//        List<GroupInfo> groupInfoList = groupInfoMapper.selectByExample(example);
//        if (groupInfoList.size() > 0){
//            GroupInfo groupInfo = groupInfoList.get(0);
//            Map memberParams = new HashMap();
//            memberParams.put("members",groupInfo.getGroupmembers().split(","));
//            List<Account> members = custGroupInfoMapper.queryGroupMembersAccount(memberParams);
//            for (Account account : members){
//                chatRecord.setMessagetoid(account.getAccount());
//                //插入聊天列表
//                int insert = chatRecordMapper.insert(chatRecord);
//                //插入聊天回执列表
//                String from = chatRecord.getMessagefromid();
//                String to = chatRecord.getMessagetoid();
//                ChatAckExample exampleAck = new ChatAckExample();
//                exampleAck.createCriteria().andMessageidEqualTo(chatRecord.getMessageid()).andMessagetoidEqualTo(to);
//                List<ChatAck> listAck = chatAckMapper.selectByExample(exampleAck);
//                if (listAck == null || listAck.size() == 0){
//                    ChatAck chatAck = new ChatAck();
//                    chatAck.setMessageid(chatRecord.getMessageid());
//                    chatAck.setMessagetoid(to);
//                    chatAck.setMessagetime(chatRecord.getMessagetime());
//                    if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to) && from.equals(to)){
//                        chatAck.setMessageack(1);
//                    } else {
//                        chatAck.setMessageack(0);
//                    }
//                    int insertAck = chatAckMapper.insert(chatAck);
//                }
//            }
//        }
//    }
}
