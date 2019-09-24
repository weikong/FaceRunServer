package com.facerun.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.ChatAck;
import com.facerun.bean.ChatAckExample;
import com.facerun.bean.ChatRecord;
import com.facerun.dao.ChatAckMapper;
import com.facerun.dao.ChatRecordMapper;
import com.facerun.dao.CustChatRecordAckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
                    client.setSoTimeout(15*1000);
                    String getHostAddress = client.getInetAddress().getHostAddress();
                    if (serviceMap.containsKey(getHostAddress)) {
                        Service service = serviceMap.get(getHostAddress);
                        service.setSocket(client);
                    } else {
                        Service service = new Service(client);
                        serviceMap.put(getHostAddress, service);
                        mExecutorService.execute(service);
                    }
                } catch (Exception e){
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

        public void stopService(){
            setStop = true;
        }

        @Override
        public synchronized void run() {
            try {
                while (!setStop) {
                    System.out.println("setStop:" + setStop+"  isConnected = "+socket.isConnected());
                    //循环接收、读取 Client 端发送过来的信息
//                    if (in != null && (receiveMsg = in.readLine()) != null) {
                    if (in != null && (receiveMsg = in.readLine()) != null) {
                        if (StringUtils.isEmpty(receiveMsg) || receiveMsg == null) {
                            System.out.println("receiveMsg:" + receiveMsg +"  getPort = "+socket.getPort());
                            return;
                        }
                        ChatRecord chatRecord = JSONObject.parseObject(receiveMsg, ChatRecord.class);
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
                                if (serviceMap.containsKey(hd)){
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
                                if (IPAddressIDMap.containsKey(userId)){
                                    String ip = IPAddressIDMap.get(userId);
                                    if (serviceMap.containsKey(ip)){
                                        Service service = serviceMap.get(ip);
                                        System.out.println("service.serviceId: " + service.getServiceId());
                                        if (!service.getServiceId().equals(serviceId)){
                                            service.stopService();
                                            serviceMap.remove(ip);
                                        }
                                    }
                                }
                                IPAddressIDMap.put(userId,getHostAddress);

                                //清除之前的PrintWriter
                                if (printWriterMap.containsKey(userId)){
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
                                    example.createCriteria().andMessageidEqualTo(chatRecord.getMessageid()).andMessagetoidEqualTo(chatRecord.getMessagefromid());
                                    List<ChatAck> list = chatAckMapper.selectByExample(example);
                                    if (list != null && list.size() == 1){
                                        ChatAck chatAck = list.get(0);
                                        chatAck.setMessageack(1);
                                        chatAckMapper.updateByPrimaryKey(chatAck);
                                    }
                                    System.out.println(receiveMsg);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                break;
                            case 9: //聊天内容
                                //向 Client 端反馈、发送信息
//                                printWriter.println(JSON.toJSONString(ackClient(chatRecord.getMessageid())));
                                int insert = 0;
                                try {
                                    chatRecord.setMessagetime(new Date());
                                    chatRecord.setMessagestate(1);
                                    insert = chatRecordMapper.insert(chatRecord);
                                    if (insert == 0)
                                        return;
                                    String to = chatRecord.getMessagetoid();
                                    ChatAck chatAck = new ChatAck();
                                    chatAck.setMessageid(chatRecord.getMessageid());
                                    chatAck.setMessagetoid(to);
                                    chatAck.setMessagetime(chatRecord.getMessagetime());
                                    chatAck.setMessageack(0);
                                    int insertAck = chatAckMapper.insert(chatAck);
                                    if (!StringUtils.isEmpty(to) && printWriterMap.containsKey(to)) {
                                        PrintWriter pw = printWriterMap.get(to);
                                        if (pw != null && pw != printWriter) {
                                            chatRecord.setMessagestate(1);
                                            pw.println(JSON.toJSONString(chatRecord));
                                        }
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                } finally {
                                    chatRecord.setMessagetype(5);
                                    if (insert == 0){
                                        chatRecord.setMessagestate(9);
                                        chatRecordMapper.updateByPrimaryKey(chatRecord);
                                    }
                                    printWriter.println(JSON.toJSONString(chatRecord));
                                }
                                break;
                        }
                    } else {
                        String getHostAddress = socket.getInetAddress().getHostAddress();
                        if (!StringUtils.isEmpty(getHostAddress) && serviceMap.containsKey(getHostAddress)){
                            serviceMap.remove(getHostAddress);
                        }
                        Iterator<Map.Entry<String, String>> entries = IPAddressIDMap.entrySet().iterator();
                        String IpAddressKey = "";
                        while(entries.hasNext()){
                            Map.Entry<String, String> entry = entries.next();
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (getHostAddress.equals(value)){
                                IpAddressKey = key;
                                break;
                            }
                        }
                        if (!StringUtils.isEmpty(IpAddressKey)){
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
                stopService();
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

    public String buildOfflineData(String toId){
        List<ChatRecord> list = new ArrayList<>();
        Map params = new HashMap();
        params.put("toid",toId);
        List<ChatRecord> chatRecordList = custChatRecordAckMapper.queryChatRecordAck(params);
        if (chatRecordList == null || chatRecordList.size() == 0)
            return "";
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setMessagecontent("离线数据");
        chatRecord.setMessagetype(6);
        chatRecord.setMessageid(UUID.randomUUID().toString());
        chatRecord.setMessagecontent(JSON.toJSONString(chatRecordList));
        return JSON.toJSONString(chatRecord);
    }
}
