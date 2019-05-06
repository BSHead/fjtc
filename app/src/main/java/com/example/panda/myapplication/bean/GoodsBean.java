package com.example.panda.myapplication.bean;


/**
 * Created by zzl_w on 2018/7/18.
 */


public class GoodsBean extends BaseBean {
    private String goodsId;
    private String goodsName;
    private String goodsImage;
    private double goodsPrice;
    private String taste;//“1”代表左侧A出口的口味，“2”代表中间，“3”代表右侧B出口

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
}
