package org.miles.lib.data.kaiyan;

import org.miles.lib.data.AppDatabase;
import org.miles.lib.data.kaiyan.db.KaiyanCategoryDao;
import org.miles.lib.data.kaiyan.db.KaiyanVideoItemDao;

public class KaiyanDaoImpl implements KaiyanDataSource.Dao {
    @Override
    public KaiyanCategoryDao kaiyanCategoryDao() {
        return AppDatabase.get().kaiyanCategoryDao();
    }

    @Override
    public KaiyanVideoItemDao kaiyanVideoItemDao() {
        return AppDatabase.get().kaiyanVideoItemDao();
    }
}
