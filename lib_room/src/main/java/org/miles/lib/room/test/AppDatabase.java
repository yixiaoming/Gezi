package org.miles.lib.room.test;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.miles.lib.room.AppConfig;
import org.miles.lib.room.BaseEntity;
import org.miles.lib.room.BaseEntityDao;

@Database(entities = {BaseEntity.class, User.class, Address.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "gezi.db";

    private static volatile AppDatabase sAppDatabase;

    public abstract BaseEntityDao baseEntityDao();

    public abstract UserDao userDao();

    public abstract UserAddressesDao userAddressesDao();

    public abstract AddressDao addressDao();

    public static AppDatabase get() {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = Room.databaseBuilder(AppConfig.getContext(),
                            AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return sAppDatabase;
    }

    @VisibleForTesting
    public static AppDatabase get(Context context) {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
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
}