package com.facerun.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by maesinfo on 2018/8/30.
 */
public class ServerSocketUtil {
    public static int PORT = 6677;

    public static HashMap<String, Socket> socketMap = new HashMap<>();

    public static HashMap<String, DataOutputStream> outputStreamMap = new HashMap<>();

    private static Timer timer;
    private static TimerTask timerTask;

    public static void main(String[] args) {
        System.out.println("服务器启动...");
//        ServerSocketUtil server = new ServerSocketUtil();
//        server.init();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        for (String key : outputStreamMap.keySet()) {
                            Socket sc = socketMap.get(key);
                            String strClient = "";
//                            try {
//                                InputStream is = sc.getInputStream();
//                                DataInputStream input = new DataInputStream(is);
//                                strClient = input.readUTF();
//                                System.out.println(System.currentTimeMillis() + " 处理客户端数据 strClient：" + strClient);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            DataOutputStream out = outputStreamMap.get(key);
                            String random = getRandomStr();
                            out.writeUTF(key + "  " + strClient);//.writeUTF(random);
                            out.flush();
                            System.out.println(System.currentTimeMillis() + " 处理客户端数据 push：" + strClient);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(System.currentTimeMillis() + " 处理客户端数据 InterruptedException：");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println(System.currentTimeMillis() + " 处理客户端数据 IOException：");
                    }


                }
            }
        }).start();
    }

    static Socket socket = null;

    public static void test() {
        ServerSocket server = null;
        DataOutputStream out = null;
        try {
            server = new ServerSocket(PORT);
//            while (true) {
            System.out.println("处理客户端数据：1111");
            socket = server.accept();
            System.out.println("处理客户端数据：2222");
            DataInputStream input = new DataInputStream(socket.getInputStream());
            String strClient = input.readUTF();
            System.out.println("处理客户端数据：" + strClient);
            out = new DataOutputStream(socket.getOutputStream());
            String id = strClient.split(":")[0];
            outputStreamMap.put(id, out);
            socketMap.put(id, socket);
            String random = getRandomStr();
            System.out.println("处理客户端数据：4444 random = " + random);
            out.writeUTF(random);
            out.flush();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getRandomStr() {
        String str = "";
        int ID = (int) (Math.random() * 30);
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 300);
        int z = (int) (Math.random() * 10);
        str = "ID:" + ID + "/x:" + x + "/y:" + y + "/z:" + z;
        return str;
    }
}
