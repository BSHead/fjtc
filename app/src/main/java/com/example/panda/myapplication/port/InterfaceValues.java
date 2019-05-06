package com.example.panda.myapplication.port;

public interface InterfaceValues {
    /**
     * 极光IM的appKey
     */
    String JMessageAppKey = "28f33c0ff1c169e3539b0884";

    /**
     * 冰淇淋机的统一极光IM密码，用户名是获取的UUID
     */
    String JMessagePassword = "123456zzllll";

    /**
     * 机器在线间隔时间，当前每隔30秒上传一次
     * 一秒钟为1000毫秒
     */
    long DELAY_MILLS = 1000 * 30;//正常是30秒，10秒做测试


}
