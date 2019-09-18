package com.facerun.util;

import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by maesinfo on 2019/9/18.
 */

public class SocketPrintBean implements Serializable {

    String userId;
    String hostName;
    PrintWriter printWriter;
    Socket socket;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
