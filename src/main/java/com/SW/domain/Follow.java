package com.SW.domain;

import java.io.Serializable;

public class Follow implements Serializable {
    private String myUID;
    private String targetUID;
    private String time;

    public String getTime() {
        return time;
    }

    public String getMyUID() {
        return myUID;
    }

    public String getTargetUID() {
        return targetUID;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMyUID(String myUID) {
        this.myUID = myUID;
    }

    public void setTargetUID(String targetUID) {
        this.targetUID = targetUID;
    }
}
