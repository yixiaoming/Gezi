package org.miles.lib.data;

import android.content.Context;

import org.miles.lib.data.kaiyan.KaiyanApiImpl;
import org.miles.lib.data.kaiyan.KaiyanDaoImpl;
import org.miles.lib.data.kaiyan.KaiyanDataSource;

public class DataManager {

    public static void init(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase.init(appContext);
        initDataSources();
    }

    private static void initDataSources() {
        KaiyanDataSource.initDao(new KaiyanDaoImpl());
        KaiyanDataSource.initApi(new KaiyanApiImpl());
    }
}
