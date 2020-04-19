package com.SW.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class User implements Serializable {
    @JsonProperty(value = "Username")
    private String Username;
    @JsonProperty(value = "Password")
    private String Password;
    @JsonProperty(value = "UID")
    private String UID;
    private String sex;
    private int age;
    private String introduce;
    private int isFollowed=0;
    private String registerTime;
    private int followNum;
    private int fanNum;
    private int messageNum;

    public int getFanNum() {
        return fanNum;
    }

    public int getFollowNum() {
        return followNum;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setFanNum(int fanNum) {
        this.fanNum = fanNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public int getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(int isFollowed) {
        this.isFollowed = isFollowed;
    }

    public int getAge() {
        return age;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getSex() {
        return sex;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
