package com.example.panda.myapplication.bean;

import java.io.Serializable;

public class BaseBean implements Serializable {
    public String state;
    public String msg;
    public String mt;//消息类型，和接口名称保持一致
    public String userId;//用户id
    public String shelfId;//当前用户下的冰淇淋机id
    public String userid;
    public String shelfid;
    public String type;
    public String shelfState;
    public String  index;
    public String id;
    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getShelfState() {
        return shelfState;
    }

    public void setShelfState(String shelfState) {
        this.shelfState = shelfState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShelfid() {
        return shelfid;
    }

    public void setShelfid(String shelfid) {
        this.shelfid = shelfid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }
}
