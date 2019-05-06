package com.example.panda.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {
    //存账号的方法
    public static void setName(Context context, String name){
        SharedPreferences sharedPreferences=context.getSharedPreferences("name",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",name);
        editor.commit();
    }
    //取的方法
    public static String getName(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("name",0);
        return   sharedPreferences.getString("name","");
    }
    //存密码
    public  static void setPwd(Context context,String pwd){
        SharedPreferences sharedPreferences=context.getSharedPreferences("pwd",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("pwd",pwd);
        editor.commit();
    }
    //取的密码方法
    public  static String getPwd(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("pwd",0);
        return   sharedPreferences.getString("pwd","");
    }

    //存1
    public  static void setclean(Context context,String clean){
        SharedPreferences sharedPreferences=context.getSharedPreferences("clean",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("clean",clean);
        editor.commit();
    }
    //取1
    public  static String getclean(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("clean",0);
        return   sharedPreferences.getString("clean","");
    }
}
