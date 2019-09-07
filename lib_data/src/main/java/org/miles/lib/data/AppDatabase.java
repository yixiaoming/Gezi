package org.miles.lib.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.miles.lib.data.gank.db.GankDao;
import org.miles.lib.data.gank.entity.GankCategoryEntity;
import org.miles.lib.data.gank.entity.GankEntity;
import org.miles.lib.data.gank.entity.GankTabEntity;
import org.miles.lib.data.kaiyan.db.KaiyanCategoryDao;
import org.miles.lib.data.kaiyan.db.KaiyanVideoItemDao;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoItem;

@Database(entities = {KaiyanCategory.class, KaiyanVideoItem.class, GankEntity.class,
        GankCategoryEntity.class, GankTabEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "gezi.db";

    private static volatile AppDatabase sAppDatabase;
    private static Context sAppContext;

    public static void init(@NonNull Context context) {
        sAppContext = context.getApplicationContext();
    }

    public static AppDatabase get() {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = Room.databaseBuilder(sAppContext,
                            AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return sAppDatabase;
    }

    @VisibleForTesting
    public static AppDatabase getAllowMainThread() {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = Room.databaseBuilder(sAppContext,
                            AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return sAppDatabase;
    }

    @VisibleForTesting
    public void clear() {
        sAppDatabase = null;
    }

    public abstract KaiyanCategoryDao kaiyanCategoryDao();

    public abstract KaiyanVideoItemDao kaiyanVideoItemDao();

    public abstract GankDao gankDao();
}
