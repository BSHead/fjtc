package com.example.panda.myapplication.bean;

import java.util.List;

/**
 * Created by zzl_w on 2018/7/18.
 */

public class ResponseMachineDetailListBean extends BaseBean {
    private List<GoodsBean> shelfTasteList;

    public List<GoodsBean> getShelfTasteList() {
        return shelfTasteList;
    }

    public void setShelfTasteList(List<GoodsBean> shelfTasteList) {
        this.shelfTasteList = shelfTasteList;
    }
}