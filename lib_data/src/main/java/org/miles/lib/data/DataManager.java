package org.miles.lib.data;

import android.content.Context;

import org.miles.gank.data.GankDataSource;
import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.lib.data.gank.GankApiImpl;
import org.miles.lib.data.gank.GankDaoImpl;
import org.miles.lib.data.kaiyan.KaiyanApiImpl;
import org.miles.lib.data.kaiyan.KaiyanDaoImpl;

public class DataManager {

    public static void init(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase.init(appContext);
        initDataSources();
    }

    private static void initDataSources() {
        KaiyanDataSource.initDao(new KaiyanDaoImpl());
        KaiyanDataSource.initApi(new KaiyanApiImpl());
        GankDataSource.initDao(new GankDaoImpl());
        GankDataSource.initApi(new GankApiImpl());
    }
}
