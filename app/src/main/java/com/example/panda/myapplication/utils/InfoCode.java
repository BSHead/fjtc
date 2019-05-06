package com.example.panda.myapplication.utils;

public class InfoCode {
    /**
     * 服务器相关状态码
     */
    public static final int SERVER_OK = 200;//服务器返正常返回数据
    public static final int SERVER_ERROR = 500;//服务器异常
    public static final int SERVER_NOT_CONNECT = 404;//未连接服务器，可能是网络问题，或者访问地址不存在等

    /**
     * 服务器正常后，接口的状态码,1000成功，1001失败
     */
    public static final String TASK_SUCCESS = "1001";//业务回执成功
    public static final String TASK_FAIL = "1000";//业务回执失败

//    //遍历请求头信息
//    Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//        Log.d("123", headers.name(i) + ":" + headers.value(i));
//    }
}
