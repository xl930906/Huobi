package com.android.HuoBiAssistant.util;

import android.util.Log;



/**
 * Created by Dragon丶Lz on 2016/4/15.
 */
public class LogUtils {

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;
    private static final String TAG = "HuobiAssisantBug---->";

    public static void i(String msg) {
        if (isDebug) Log.i(TAG, msg);
    }

    public static void d(String msg,boolean isShow) {
        if (isDebug) {
            if (isShow) {
                Log.d(TAG, msg);
            }
        }
    }

    public static void e(String msg) {
        if (isDebug) Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug) Log.v(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void printHttpValue(String url,String method,String sign,Long Timestamp,String coinType ){
        if (isDebug) Log.d(TAG, "URL="+url+";"+"Method="+method+";"+"Sign="+sign+";"+"Timestamp="+Timestamp+";"+"CoinType="+coinType+";");
    }





}

