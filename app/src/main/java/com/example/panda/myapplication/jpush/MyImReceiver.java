package com.example.panda.myapplication.jpush;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.panda.myapplication.activity.MainActivity;
import com.example.panda.myapplication.bean.JpushBean;
import com.example.panda.myapplication.operation_activity.OperationPageActivity;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.google.gson.Gson;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zzl on 2018/5/29.
 * 极光接收推送消息的广播
 */
public class MyImReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.e("收到通知内容,action的值是: ", intent.getAction());
//        每个 Receiver action 详细解释  https://docs.jiguang.cn/jpush/client/Android/android_api/#receiver
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            /**
             * SDK 向 JPush Server 注册所得到的注册 ID。一般来说，可不处理此广播信息。要深入地集成极光推送，开发者想要自己保存 App 用户与
             * JPush 用户关系时，则接受此广播，取得 Registration ID 并保存与 App uid 的关系到开发者自己的应用服务器上。
             */
//            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//            L.d("[MyReceiver] 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            /**
             * 收到了自定义消息 Push。注：用web界面发送的推送消息不会在这里收到属于下面的 ACTION_NOTIFICATION_RECEIVED
             * SDK 对自定义消息，只是传递，不会有任何界面上的展示。自定义消息不会展示在通知栏，完全要开发者写代码去处理
             * 如果开发者想推送自定义消息 Push，则需要在 AndroidManifest.xml 里配置此 Receiver action，并且在自己写的 BroadcastReceiver 里接收处理。
             */
            // String params = EncryptUtils.aesDecryptString(bundle.getString(JPushInterface.EXTRA_MESSAGE));
            //  Log.d("Api消息消息内容是" , params);
            // EventBus.getDefault().post(new MyMessageEvent(params));//eventBus发送消息
            //1.消息标题
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            //2.消息内容
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            //3.消息附加字段
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            //4.消息唯一标识ID
            String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Log.e("TTT",title+content+extra+msgId+"");
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d("收到了web发来的通知" ,JPushInterface.EXTRA_MESSAGE);
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
            Log.d("收到了message : " , message);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.e("收到了extra",extra);
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            Log.e("收到了title",title);
            /**
             * 收到了通知 Push。这里能接受web后台发来的通知，收不到自定义消息(接口发来的通知收不到)
             * 如果通知的内容为空，则在通知栏上不会展示通知。
             * 但是，这个广播 Intent 还是会有。开发者可以取到通知内容外的其他信息。
             */
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d("用户点击打开了通知","");
//            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context,MainActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d("Unhandled intent - ", intent.getAction());
        }
    }
}
