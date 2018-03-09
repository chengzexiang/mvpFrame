package com.xzkj.xzkjproject.utils;

import android.util.Log;

public class LogsUtil {
    public static boolean isLog = true;
    private static final String logMsg = "xzLog";

    public enum LogType {
        v,
        d,
        i,
        w,
        e
    }

    public static void v(String msg) {
        showLog(LogType.v, msg);
    }

    public static void d(String msg) {
        showLog(LogType.d, msg);
    }

    public static void i(String msg) {
        showLog(LogType.i, msg);
    }

    public static void w(String msg) {
        showLog(LogType.w, msg);
    }

    public static void e(String msg) {
        showLog(LogType.e, msg);
    }


    private static void showLog(LogType logType, String msg) {
        if (isLog) {
            switch (logType) {
                case v:
                    Log.v(logMsg, msg);
                    break;
                case d:
                    Log.d(logMsg, msg);
                    break;
                case i:
                    Log.i(logMsg, msg);
                    break;
                case w:
                    Log.w(logMsg, msg);
                    break;
                case e:
                    Log.e(logMsg, msg);
                    break;
            }
        }
    }
}
