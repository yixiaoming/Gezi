package org.miles.gezi.module.app;

import android.app.Application;
import android.content.Context;

import org.miles.lib.data.DataManager;

public class App extends Application {

    private static Context sAppContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sAppContext = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(sAppContext);
    }

    public static Context get() {
        return sAppContext;
    }
}
