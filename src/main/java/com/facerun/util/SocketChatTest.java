package com.facerun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by maesinfo on 2019/9/17.
 */
public class SocketChatTest {

    private static final int PORT = 9999;
    private Map<String, PrintWriter> printWriterMap = new HashMap<>();
    private Map<String, Service> serviceMap = new HashMap<>();
    private ServerSocket server = null;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;

    public static void main(String[] args) {
        new SocketChatTest();
    }

    public SocketChatTest() {
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
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter printWriter = null;
        private boolean hasService = false;

        public Service(Socket socket) {
            //这段代码对应步骤三
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                printWriter.println(connectSuccess());
                System.out.println(connectSuccess());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setSocket(Socket socket) {
            //这段代码对应步骤三
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                printWriter.println(connectSuccess());
                System.out.println(connectSuccess());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public synchronized void run() {
            try {
                while (true) {
                    //循环接收、读取 Client 端发送过来的信息
                    if (in != null && (receiveMsg = in.readLine()) != null) {
                        if (StringUtils.isEmpty(receiveMsg) || receiveMsg == null) {
                            return;
                        }
                        SocketBaseBean socketBaseBean = JSONObject.parseObject(receiveMsg, SocketBaseBean.class);
                        System.out.println("receiveMsg:" + receiveMsg);
                        /**
                         * type 9：聊天内容； 1：Ping； 2：Connect；3：Disconnect； 4：Login
                         * */
                        int type = socketBaseBean.getType();
                        switch (type) {
                            case 1: //Ping
                                printWriter.println(receiveMsg);
                                break;
                            case 2: //Connect
                                break;
                            case 3: //Disconnect
                                System.out.println(disconnect());
                                printWriter.println(disconnect());
                                String id = socketBaseBean.getUserId();
                                if (printWriterMap.containsKey(id)) {
                                    printWriterMap.remove(id);
                                }
                                in.close();
                                in = null;
                                //接受 Client 端的断开连接请求，并关闭 Socket 连接
                                socket.close();
                                socket = null;
                                break;
                            case 4: //Login
                                printWriterMap.put(socketBaseBean.getFrom(), printWriter);
                                printWriter.println(receiveMsg);
                                break;
                            case 9: //聊天内容
                                //向 Client 端反馈、发送信息
                                String to = socketBaseBean.getTo();
                                if (!StringUtils.isEmpty(to) && printWriterMap.containsKey(to)) {
                                    PrintWriter pw = printWriterMap.get(to);
                                    if (pw != null) {
                                        pw.println(receiveMsg);
                                    }
                                }
                                printWriter.println(receiveMsg);
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
}
