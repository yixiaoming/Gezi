package org.miles.gezi.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.miles.gezi.model.entity.User;
import org.miles.gezi.model.entity.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "gezi.db";

    private static volatile AppDatabase sAppDatabase;
    private static Context sAppContext;

    public abstract UserDao userDao();

    public static void init(@NonNull Context context) {
        sAppContext = context.getApplicationContext();
    }

    public static AppDatabase get() {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = Room.databaseBuilder(sAppContext, AppDatabase.class, DB_NAME)
                            .build();
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
                    sAppDatabase = Room.databaseBuilder(sAppContext, AppDatabase.class, DB_NAME)
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