package com.facerun.bean;

import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnPrint extends SocketConn {

    private PrintWriter printWriter;
    private Socket socket;

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
