package org.miles.kaiyan.data;

import org.miles.kaiyan.data.api.KaiyanApi;
import org.miles.kaiyan.data.db.KaiyanCategoryDao;
import org.miles.kaiyan.data.db.KaiyanVideoItemDao;

public class KaiyanDataSource {

    private static Dao sDao;
    private static Api sApi;

    public interface Dao {
        KaiyanCategoryDao kaiyanCategoryDao();

        KaiyanVideoItemDao kaiyanVideoItemDao();
    }

    public interface Api {
        KaiyanApi kaiyanApi();
    }

    public static void initDao(Dao dao) {
        sDao = dao;
    }

    public static void initApi(Api api) {
        sApi = api;
    }

    public static Dao dao() {
        return sDao;
    }

    public static Api api() {
        return sApi;
    }
}
