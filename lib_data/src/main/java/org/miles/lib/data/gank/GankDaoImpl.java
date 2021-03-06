package org.miles.lib.data.gank;

import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.db.GankXianduDao;
import org.miles.lib.data.AppDatabase;
import org.miles.gank.data.db.GankDao;

public class GankDaoImpl implements GankDataSource.Dao {
    @Override
    public GankDao gankDao() {
        return AppDatabase.get().gankDao();
    }

    @Override
    public GankXianduDao gankXianduDao() {
        return AppDatabase.get().gankXianduDao();
    }
}
