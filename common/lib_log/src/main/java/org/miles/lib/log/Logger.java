package org.miles.lib.log;

import android.util.Log;

public class Logger {

    public static int LEVEL = Log.DEBUG;
    public static final String APP_TAG = "Gezi";

    public static void d(String msg) {
        Log.d(APP_TAG, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(APP_TAG + ":" + tag, msg);
    }

}
