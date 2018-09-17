package com.facerun.bean;

import java.io.Serializable;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class ChatMsgBean extends AbsDomain implements Serializable {

    public String from;
    public String to;
    public String userName;
    public String msg;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
