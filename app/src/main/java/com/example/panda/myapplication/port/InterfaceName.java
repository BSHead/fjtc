package com.example.panda.myapplication.port;

/**
 * Created by zzl_w on 2018/7/12.
 */

public interface InterfaceName {
    /**
     * 1.app/***的接口是商户运维短的
     * 2./***的接口是上位机的
     */
    /**
     * app接口
     */

    /**
     * 用户登录
     */
    String LOGIN = "app/login.do";
    /**
     * 清洗机器
     */
    String CLEAR = "app/insCleanCon.do";
    /**
     * 商户售卖机列表
     */
    String MACHINE_LIST = "app/shelfInfoList.do";

    /**
     * 可售卖商品列表
     */
    String GOODS_LIST = "app/getGoodsList.do";

    /**
     * 设置商品信息，价格口味
     */
    String SETTING_GOODS_INFO = "app/uploadTasteAndPrice.do";

    /**
     * 设置商品信息，价格口味
     */
    String GET_MACHINE_GOODS_INFO = "app/getShelfTasteList.do";
    /**
     * 上位机接口
     */
    /**
     * 请求轮播图信息
     */
    String GET_CAROUSEL_FIGURE_LIST = "carouselfigureList.do";
    /**
     * 机器在线 每隔30秒通知服务器
     */
    String MACHINE_ONLINE = "shelfLogin.do";
    /**
     * 当前订单是否可做
     */
    String ORDER_STATE = "getOrdersState.do";
    /**
     * 通知补货完成addReplen
     */
    String ADDREPLEN = "app/addReplenishGoods.do";
    /**
     * 极光IM简单报文类型
     *
     */
    /**
     * 控制出料口
     */
    String CONTROL_DISCHARGE_PORT = "controlDischargePort";

    String PUSH = "uploadShelfInfoState.do";

    /**
     * 通知
     */
    String Tong = "app/selPushMessageByShelfidCon.do";
/**
 * 获取机器的经纬度
 */
String LL = "app/findShelfByUserid.do";
    /**
     * 通知状态
     */

    String TSTATE = "/app/savePushMessageStateByIdCon.do";

    /**
     * 获取机器现在的状态
     */

        String JiQIZT = "app/findMessageTypeIdByShelfid.do";

    /**
     * 上传机器状态
     */
        String UPSTATE = "uploadShelfInfoState.do";
    /**
     * 出料口统一类型，0左侧，1中间，2右侧
     */
    String PORT_LEFT = "0";
    String PORT_MIDDLE = "1";
    String PORT_RIGHT = "2";
}
