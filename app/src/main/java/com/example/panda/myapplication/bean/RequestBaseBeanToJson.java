package com.example.panda.myapplication.bean;

import com.example.panda.myapplication.utils.GsonUtility;

/**
 * Created by zzl_w on 2018/7/16.
 */

public class RequestBaseBeanToJson {

    /**
     * 根据用户请求数据
     *
     * @param userId
     * @return
     */
    public static String requestBaseBeanToJson(String userId) {
        BaseBean bean = new BaseBean();
        bean.setUserId(userId);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }

    /**
     * 根据机器请求数据
     *
     * @param shelfId
     * @return
     */
    public static String requestBaseBeanShelfIdToJson(String shelfId) {
        BaseBean bean = new BaseBean();
        bean.setShelfId(shelfId);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }


}
