package com.example.panda.myapplication.utils;

import com.example.panda.myapplication.bean.BaseBean;

/**
 * Created by zzl_w on 2018/7/12.
 * bean转换成json
 */

public class RequestLoginBeanToJson {

    public static String requestLoginBeanToJson(String userId, String password) {
        LoginBean bean = new LoginBean(userId, password);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }

    public static String requestClearBeanToJson(String userid, String shelfid,String  state) {
        BaseBean bean = new BaseBean();
        bean.setUserid(userid);
        bean.setShelfid(shelfid);
        bean.setState(state);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    public static String requestaddBeanToJson(String userid, String shelfid) {
        BaseBean bean = new BaseBean();
        bean.setUserid(userid);
        bean.setShelfid(shelfid);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    public static String requesttestBeanToJson( String shelfid,String shelfState) {
        BaseBean bean = new BaseBean();
        bean.setShelfId(shelfid);
        bean.setShelfState(shelfState);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    /**
     *获取机器的经纬度
     */
    public static String requestllBeanToJson(String name) {
        BaseBean bean = new BaseBean();
        bean.setUserid(name);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    /**
     * 我的通知
     */
    public static String requesttongBeanToJson( String shelfid,String i) {
        BaseBean bean = new BaseBean();
        bean.setIndex(i);
        bean.setUserid(shelfid);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    /**
     * 通知状态d
     */
    public static String requeststateToJson( String id) {
        BaseBean bean = new BaseBean();
        bean.setId(id);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    /**
     * 获取机器此时的状态
     */
    public static String requestjqztToJson( String id) {
        BaseBean bean = new BaseBean();
        bean.setShelfid(id);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }
    /**
     * 内部类
     */
    static class LoginBean extends BaseBean {
        private String password;

        private LoginBean(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }
    }


}
