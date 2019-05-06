package com.example.panda.myapplication.utils;

import com.example.panda.myapplication.bean.BaseBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtility {
    /**
     * 将bean转为Json,发送
     * @param bean
     * @return
     */
    public static String bean2Json(BaseBean bean){
        Gson gson=new Gson();
        return gson.toJson(bean).toString();
    }

    /**
     * 将Json转为bean,用于接收
     * @param jsonStr
     * @param bean
     * @param <T>
     * @return
     */

    public static <T> T json2Bean(String jsonStr, Class<T> bean){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson=gsonBuilder.setPrettyPrinting().create();
        return gson.fromJson(jsonStr,bean);
    }

}
