package com.example.panda.myapplication.bean;

public class NoticeBean {
/**
 * id , shelid, content, state,createTime
 */
   private int id;
   private String shelid;
   private String content;
   private String state;
   private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShelid() {
        return shelid;
    }

    public void setShelid(String shelid) {
        this.shelid = shelid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
