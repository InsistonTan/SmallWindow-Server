package com.SW.domain;

import java.io.Serializable;

public class Collect implements Serializable {
    private int id;
    private int msg_index;
    private String uid;
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMsg_index() {
        return msg_index;
    }

    public void setMsg_index(int msg_index) {
        this.msg_index = msg_index;
    }
}
