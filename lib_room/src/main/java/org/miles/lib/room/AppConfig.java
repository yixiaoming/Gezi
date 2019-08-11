package org.miles.lib.room;

import android.content.Context;

public class AppConfig {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
