package com.example.panda.myapplication.bean;

import java.util.List;

/**
 * Created by zzl_w on 2018/7/18.
 * 冰淇淋口味列表
 */

public class ResponseGoodsListBean extends BaseBean {
    private List<GoodsBean> goodsList;

    public List<GoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsBean> goodsList) {
        this.goodsList = goodsList;
    }
}