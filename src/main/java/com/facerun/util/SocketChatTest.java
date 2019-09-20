package com.facerun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.ChatRecord;
import com.facerun.bean.ChatRecordExample;
import com.facerun.dao.ChatRecordMapper;
import com.facerun.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
    private AccountService accountService;

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
                client = server.accept();
                String getHostAddress = client.getInetAddress().getHostAddress();
                if (serviceMap.containsKey(getHostAddress)) {
                    Service service = serviceMap.get(getHostAddress);
                    service.setSocket(client);
                } else {
                    Service service = new Service(client);
                    serviceMap.put(getHostAddress, service);
                    mExecutorService.execute(service);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    //循环接收、读取 Client 端发送过来的信息
                    if (in != null && (receiveMsg = in.readLine()) != null) {
                        if (StringUtils.isEmpty(receiveMsg) || receiveMsg == null) {
                            System.out.println("receiveMsg:" + receiveMsg +"  getPort = "+socket.getPort());
                            return;
                        }
                        SocketBaseBean socketBaseBean = JSONObject.parseObject(receiveMsg, SocketBaseBean.class);
                        System.out.println("receiveMsg:2 " + JSON.toJSONString(socketBaseBean));
                        /**
                         * type 9：聊天内容； 1：Ping； 2：Connect；3：Disconnect； 4：Login
                         * */
                        int type = socketBaseBean.getType();
                        switch (type) {
                            case 1: //Ping
                                System.out.println("receiveMsg:Ping");
                                printWriter.println(JSON.toJSONString(socketBaseBean));
                                break;
                            case 2: //Connect
                                System.out.println("receiveMsg:Connect");
                                break;
                            case 3: //Disconnect
                                System.out.println(disconnect());
                                printWriter.println(disconnect());
                                String hd = socket.getInetAddress().getHostAddress();
                                String uid = socketBaseBean.getUserId();
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
                                String userId = socketBaseBean.getUserId();

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
                                printWriter.println(JSON.toJSONString(socketBaseBean));
                                break;
                            case 5:
                                try {
                                    ChatRecordExample example = new ChatRecordExample();
                                    example.createCriteria().andMessageidEqualTo(socketBaseBean.getMessageId());
                                    List<ChatRecord> list = chatRecordMapper.selectByExample(example);
                                    if (list != null && list.size() > 0){
                                        for (ChatRecord chatRecord : list){
                                            chatRecord.setMessagestate(1);
                                            chatRecordMapper.updateByPrimaryKey(chatRecord);
                                        }
                                    }
                                    printWriter.println(ackClient());
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                break;
                            case 9: //聊天内容
                                //向 Client 端反馈、发送信息
                                printWriter.println(JSON.toJSONString(socketBaseBean));
                                ChatRecord chatRecord = new ChatRecord();
                                chatRecord.setMessagefrom(socketBaseBean.getFrom());
                                chatRecord.setMessageto(socketBaseBean.getTo());
                                chatRecord.setMessagecontent(socketBaseBean.getContent());
                                chatRecord.setMessagetype(socketBaseBean.getType());
                                chatRecord.setMessageid(socketBaseBean.getMessageId());
                                chatRecord.setMessagestate(0);
                                try {
                                    int insert = chatRecordMapper.insert(chatRecord);
                                    if (insert == 0)
                                        return;
                                } catch (Exception e){
                                    e.printStackTrace();
                                }

                                String to = socketBaseBean.getTo();
                                if (!StringUtils.isEmpty(to) && printWriterMap.containsKey(to)) {
                                    PrintWriter pw = printWriterMap.get(to);
                                    if (pw != null) {
//                                        pw.println(receiveMsg);
                                        pw.println(JSON.toJSONString(socketBaseBean));
                                    }
                                }
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String connectSuccess() {
        SocketBaseBean baseBean = new SocketBaseBean();
        baseBean.setContent("成功连接服务器");
        baseBean.setType(2);
        baseBean.setMessageId(UUID.randomUUID().toString());
        return JSON.toJSONString(baseBean);
    }

    public String disconnect() {
        SocketBaseBean baseBean = new SocketBaseBean();
        baseBean.setContent("客户端请求断开连接");
        baseBean.setType(3);
        baseBean.setMessageId(UUID.randomUUID().toString());
        return JSON.toJSONString(baseBean);
    }

    public String ackClient() {
        SocketBaseBean baseBean = new SocketBaseBean();
        baseBean.setContent("ACK");
        baseBean.setType(-1);
        return JSON.toJSONString(baseBean);
    }
}
