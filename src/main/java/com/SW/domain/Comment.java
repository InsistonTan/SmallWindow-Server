package com.SW.domain;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private int msg_index;
    private String username;
    private String uid;
    private String content;
    private String time;
    private int reply_id;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

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

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }
}
