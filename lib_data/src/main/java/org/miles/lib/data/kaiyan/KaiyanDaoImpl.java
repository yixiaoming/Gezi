package org.miles.lib.data.kaiyan;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.kaiyan.data.db.KaiyanCategoryDao;
import org.miles.kaiyan.data.db.KaiyanVideoItemDao;
import org.miles.lib.data.AppDatabase;

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
