package com.facerun.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.ChatMsgBean;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by maesinfo on 2018/8/30.
 */
public class ServerSocketUtil {
    public static int PORT = 6677;

    public static HashMap<String, String> chatMsgMap = new HashMap<>();

    public static HashMap<String, Socket> socketMap = new HashMap<>();

    private static List<ChatMsgBean> datas = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("服务器启动...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }).start();

    }

    public static void test() {
        ServerSocket server = null;
        DataOutputStream out = null;
        Socket socket = null;
        try {
            server = new ServerSocket(PORT);
            while (true) {
                System.out.println("处理客户端数据：1111");
                socket = server.accept();
                System.out.println("处理客户端数据：2222");
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String strClient = input.readUTF();
                System.out.println("处理客户端数据：" + strClient);
                ChatMsgBean chatMsgBean = parseMsg(strClient);
                out = new DataOutputStream(socket.getOutputStream());
                socketMap.put(chatMsgBean.getUserName(), socket);
                chatMsgMap.put(chatMsgBean.getFrom(), chatMsgBean.getTo());
                System.out.println("处理客户端数据：4444 random = " + chatMsgBean.getMsg());
                out.writeUTF(chatMsgBean.getMsg());
                out.flush();
                chatProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ChatMsgBean parseMsg(String msg) {
        ChatMsgBean chatMsgBean = null;
        try {
            chatMsgBean = JSON.parseObject(msg, ChatMsgBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatMsgBean;
    }

    private static void chatProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        for (String key : socketMap.keySet()) {
                            System.out.println(System.currentTimeMillis() + " key = " + key);
                            Socket sc = socketMap.get(key);
                            String strClient = "";
                            try {
                                InputStream is = sc.getInputStream();
                                DataInputStream input = new DataInputStream(is);
                                System.out.println(System.currentTimeMillis() + " 111 处理客户端数据 DataInputStream：");
                                strClient = input.readUTF();
                                System.out.println(System.currentTimeMillis() + " 222 处理客户端数据 strClient：" + strClient);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (StringUtils.isNotEmpty(strClient)) {
                                ChatMsgBean bean = parseMsg(strClient);
                                if (bean != null && socketMap.containsKey(bean.getFrom())) {
                                    DataOutputStream out = new DataOutputStream(socketMap.get(bean.getFrom()).getOutputStream());
                                    out.writeUTF("阿斯蒂芬卡死了开发");//.writeUTF(random);
                                    out.flush();
                                    System.out.println(System.currentTimeMillis() + " 333 处理客户端数据 push1：" + strClient);
                                } else {
                                    System.out.println(System.currentTimeMillis() + " 333 处理客户端数据 push2：对方不在线");
                                }
                            } else {
                                System.out.println(System.currentTimeMillis() + " 333 处理客户端数据 push3：空数据");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(System.currentTimeMillis() + " 444 处理客户端数据 InterruptedException：");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println(System.currentTimeMillis() + " 555 处理客户端数据 IOException：");
                    }
                }
            }
        }).start();
    }
}
