package com.example.panda.myapplication.bean;

import java.util.List;

public class Shelf {

    /**
     * msg : 数据传输成功
     * shelfList : [{"latitude":39.921124,"longitude":116.186594}]
     * mt : app/findShelfByUserid.do
     * type : 2000
     * userid : 222222
     */

    private String msg;
    private String mt;
    private String type;
    private String userid;
    private List<ShelfListBean> shelfList;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<ShelfListBean> getShelfList() {
        return shelfList;
    }

    public void setShelfList(List<ShelfListBean> shelfList) {
        this.shelfList = shelfList;
    }

    public static class ShelfListBean {
        /**
         * latitude : 39.921124
         * longitude : 116.186594
         */

        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
