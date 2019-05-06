package com.example.panda.myapplication.utils;

import android.util.Log;

/**
 * Created by zzl_w on 2017/1/4.
 */

public class L {
    private static final String TAG = "IceCreamFilter";
    private static boolean debug = true;//设为false可关闭log
//    private static boolean debug = false;//设为false可关闭log

    public static void v(String msg) {
        if (debug) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (debug) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (debug) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (debug) {
            Log.e(TAG, msg);

        }
    }
}
