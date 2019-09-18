package com.facerun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by maesinfo on 2019/9/17.
 */
public class SocketTest {

    private static final int PORT = 9999;
    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private String sendMsg;

    public static void main(String[] args) {
        new SocketTest();
    }

    public SocketTest() {
        try {
            //步骤一
            server = new ServerSocket(PORT);
            mExecutorService = Executors.newCachedThreadPool();
            System.out.println("服务器已启动...");
            Socket client = null;
            while (true) {
                //步骤二，每接受到一个新Socket连接请求，就会新建一个Thread去处理与其之间的通信
                client = server.accept();
                mList.add(client);
                mExecutorService.execute(new Service(client));
                for (Socket socket : mList) {
                    String InetAddress = socket.getInetAddress().getHostAddress();
                    String InetHostName = socket.getInetAddress().getHostName();
                    int Port = socket.getPort();
                    String LocalAddress = socket.getLocalAddress().getHostAddress();
                    String LocalHostName = socket.getLocalAddress().getHostName();
                    int LocalPort = socket.getLocalPort();
                    System.out.println("InetAddress = " + InetAddress + "  InetHostName = " + InetHostName + "  Port = " + Port + "  LocalAddress = " + LocalAddress + "  LocalHostName = " + LocalHostName + "  LocalPort = " + LocalPort);
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

        public Service(Socket socket) {
            //这段代码对应步骤三
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), "UTF-8"));
                printWriter.println(connectSuccess());
                System.out.println(connectSuccess());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            try {
                while (true) {
                    //循环接收、读取 Client 端发送过来的信息
                    if ((receiveMsg = in.readLine()) != null) {
                        SocketBaseBean socketBaseBean = JSONObject.parseObject(receiveMsg, SocketBaseBean.class);
                        System.out.println("receiveMsg:" + receiveMsg);
                        if (socketBaseBean.getType() == 3) {
                            System.out.println(disconnect());
                            printWriter.println(disconnect());
                            mList.remove(socket);
                            in.close();
                            //接受 Client 端的断开连接请求，并关闭 Socket 连接
                            socket.close();
                            break;
                        } else {
                            sendMsg = receiveMsg;
                            //向 Client 端反馈、发送信息
                            printWriter.println(sendMsg);
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
