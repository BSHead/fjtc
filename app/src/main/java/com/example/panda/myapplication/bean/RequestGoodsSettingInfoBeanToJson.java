package com.example.panda.myapplication.bean;


import com.example.panda.myapplication.utils.GsonUtility;

/**
 * Created by zzl_w on 2018/7/18.
 */


public class RequestGoodsSettingInfoBeanToJson {

    public static String requestGoodsSettingInfoBeanToJson(String shelfId, String goodsId1, String goodsId3, double price1, double price2, double price3) {
        GoodsSettingInfoBean bean = new GoodsSettingInfoBean(shelfId, goodsId1,goodsId3, price1, price2, price3);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }


    /**
     * 内部类
     */
    static class GoodsSettingInfoBean extends BaseBean {
        private String goodsId1;
        private String goodsId3;
        private double price1;
        private double price2;
        private double price3;

        private GoodsSettingInfoBean(String shelfId, String goodsId1,String goodsId3, double price1, double price2, double price3) {
            this.shelfId = shelfId;
            this.goodsId1 = goodsId1;
            this.goodsId3 = goodsId3;
            this.price1 = price1;
            this.price2 = price2;
            this.price3 = price3;
        }
    }
}
