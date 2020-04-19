package com.SW.domain;
import java.io.Serializable;

public class Message implements Serializable {
    private String user;
    private String content;
    private String time;
    private String uid;
    private int index;
    private int view;
    private int like;
    private int liked;
    private int comment;
    private int collect;
    private int collected;

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getCollected() {
        return collected;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getIndex() {
        return index;
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
