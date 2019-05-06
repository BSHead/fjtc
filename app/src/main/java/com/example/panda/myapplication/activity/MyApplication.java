package com.example.panda.myapplication.activity;

import android.app.Application;

import com.example.panda.myapplication.utils.SharedUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
           // 初始化 JPush
        if(JPushInterface.isPushStopped(this)){
            JPushInterface.resumePush(this);//恢复极光推送
        }
        String name = SharedUtils.getName(this);
        JPushInterface.setAlias(this,1,name);
    }
}