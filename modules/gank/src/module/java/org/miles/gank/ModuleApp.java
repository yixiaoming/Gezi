package org.miles.gank;

import android.app.Application;
import android.content.Context;

import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;

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
        GankDataSource.initApi(new GankDataSource.Api() {
            @Override
            public GankApi gankApi() {
                return RetrofitManager.get().getGankApi();
            }
        });
    }

    public static Context get() {
        return sAppContext;
    }
}
