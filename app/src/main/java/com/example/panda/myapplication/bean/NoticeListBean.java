package com.example.panda.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zzl_w on 2018/7/16.
 */

public class NoticeListBean extends BaseBean {

    private List<PushDataBean> pushData;

    public List<PushDataBean> getPushData() {
        return pushData;
    }

    public void setPushData(List<PushDataBean> pushData) {
        this.pushData = pushData;
    }

    public static class PushDataBean {
        /**
         * createtime : 20190427155936
         * shelfid : 9527e3d546ce8294
         * id : 611
         * content : 111
         */

        private String createtime;
        private String shelfid;
        private String shelfidX;
        private int id;
        private String state;
        private String content;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getShelfid() {
            return shelfid;
        }

        public void setShelfid(String shelfid) {
            this.shelfid = shelfid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
