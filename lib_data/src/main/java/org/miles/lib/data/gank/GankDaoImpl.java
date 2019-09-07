package org.miles.lib.data.gank;

import org.miles.lib.data.AppDatabase;
import org.miles.lib.data.gank.db.GankDao;

public class GankDaoImpl implements GankDataSource.Dao {
    @Override
    public GankDao gankDao() {
        return AppDatabase.get().gankDao();
    }
}
