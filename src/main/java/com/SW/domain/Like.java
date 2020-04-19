package com.SW.domain;

import java.io.Serializable;

public class Like implements Serializable {
    private int id;
    private int msg_index;
    private String uid;
    private String time;

    public void setMsg_index(int msg_index) {
        this.msg_index = msg_index;
    }

    public int getMsg_index() {
        return msg_index;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
