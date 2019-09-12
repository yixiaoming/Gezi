package org.miles.gank.data;

import org.miles.gank.data.api.GankApi;
import org.miles.gank.data.db.GankDao;

public class GankDataSource {

    private static Api sApi;
    private static Dao sDao;

    public interface Api {
        GankApi gankApi();
    }

    public interface Dao {
        GankDao gankDao();
    }

    public static void initApi(Api api) {
        sApi = api;
    }

    public static void initDao(Dao dao) {
        sDao = dao;
    }

    public static Api api() {
        return sApi;
    }

    public static Dao dao() {
        return sDao;
    }
}
