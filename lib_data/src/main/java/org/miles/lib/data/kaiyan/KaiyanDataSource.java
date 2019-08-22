package org.miles.lib.data.kaiyan;

import org.miles.lib.data.kaiyan.api.KaiyanApi;
import org.miles.lib.data.kaiyan.db.KaiyanCategoryDao;
import org.miles.lib.data.kaiyan.db.KaiyanVideoItemDao;

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
