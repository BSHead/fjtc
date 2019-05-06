package com.example.panda.myapplication.bean;

/**
 * Created by zzl_w on 2018/7/16.
 */


public class MachineBean extends BaseBean {
    private String shelfImage;//设备图片
    private String address;//地址
    private double longitude;//经度
    private double latitude;
    /**
     * 设备状态
     * “0”代表异常；“1”代表正常工作；
     * “2”代表商品剩余预警（例如低于百分之30）；
     * “3”代表缺货（售卖机中没有商品）；
     * “4”代表正在补货；“5”代表正在清洗；
     * “6”代表取货口有杂物；“7”代表请立即清洗售卖机
     */
    private String shelfInfoState;


    public String getShelfImage() {
        return shelfImage;
    }

    public void setShelfImage(String shelfImage) {
        this.shelfImage = shelfImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getShelfInfoState() {
        return shelfInfoState;
    }

    public void setShelfInfoState(String shelfInfoState) {
        this.shelfInfoState = shelfInfoState;
    }
}
