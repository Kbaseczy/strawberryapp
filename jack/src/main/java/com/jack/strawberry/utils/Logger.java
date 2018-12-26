package com.jack.strawberry.utils;

import android.text.TextUtils;
import android.util.Log;

public class Logger {

    private static boolean LOG = true;
    private final static String TAG = "strawberry";

    private Logger() {

    }

    private static String getLogTag(String tag) {
        return tag == null ? TAG : tag;
    }

    //不受开关控制
    public static void info(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.i(TAG, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.i(getLogTag(tag), msg);
        }
    }


    //打印错误信息
    public static void printStackTrace(Throwable ex) {
        if (LOG) {
            if (ex != null) {
                ex.printStackTrace();
            }
        }
    }

    public static void i(String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.i(TAG, msg);
            }
        }
    }

    public static void d(String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.d(TAG, msg);
            }
        }
    }

    public static void w(String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.w(TAG, msg);
            }
        }
    }

    public static void e(String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.e(TAG, msg);
            }
        }
    }


    public static void i(String tag, String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.i(getLogTag(tag), msg);
            }
        }
    }


    public static void d(String tag, String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.d(getLogTag(tag), msg);
            }
        }
    }


    public static void w(String tag, String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.w(getLogTag(tag), msg);
            }
        }
    }


    public static void e(String tag, String msg) {
        if (LOG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.e(getLogTag(tag), msg);
            }
        }
    }

}
