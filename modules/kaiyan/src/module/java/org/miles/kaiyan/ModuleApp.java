package org.miles.kaiyan;

import android.app.Application;
import android.content.Context;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.kaiyan.data.api.KaiyanApi;

public class ModuleApp extends Application {

    private static Context sAppContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sAppContext = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KaiyanDataSource.initApi(new KaiyanDataSource.Api() {
            @Override
            public KaiyanApi kaiyanApi() {
                return RetrofitManager.get().getKaiyanApi();
            }
        });
    }

    public static Context get() {
        return sAppContext;
    }
}
