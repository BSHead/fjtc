package com.example.panda.myapplication.bean;

import java.util.List;

/**
 * Created by zzl_w on 2018/7/16.
 */

public class ResponseMachineListBean extends BaseBean {
    private List<MachineBean> shelfInfoList;

    public List<MachineBean> getShelfInfoList() {
        return shelfInfoList;
    }

    public void setShelfInfoList(List<MachineBean> shelfInfoList) {
        this.shelfInfoList = shelfInfoList;
    }
}
