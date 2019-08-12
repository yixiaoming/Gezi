package org.miles.lib.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This class is for test BaseDao
 */
@Database(entities = {BaseEntity.class}, version = 1)
public abstract class BaseTestDatabase extends RoomDatabase {

    public static final String DB_NAME = "gezi.db";

    private static volatile BaseTestDatabase sBaseTestDatabase;
    private static Context sAppContext;

    public abstract BaseEntityDao baseEntityDao();

    public static void init(@NonNull Context context) {
        sAppContext = context.getApplicationContext();
    }

    public static BaseTestDatabase get() {
        if (sBaseTestDatabase == null) {
            synchronized (BaseTestDatabase.class) {
                if (sBaseTestDatabase == null) {
                    sBaseTestDatabase = Room.databaseBuilder(sAppContext,
                            BaseTestDatabase.class, DB_NAME).build();
                }
            }
        }
        return sBaseTestDatabase;
    }

    @VisibleForTesting
    public static BaseTestDatabase getAllowMainThread() {
        if (sBaseTestDatabase == null) {
            synchronized (BaseTestDatabase.class) {
                if (sBaseTestDatabase == null) {
                    sBaseTestDatabase = Room.databaseBuilder(sAppContext,
                            BaseTestDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return sBaseTestDatabase;
    }

    @VisibleForTesting
    public void clear() {
        sBaseTestDatabase = null;
    }
}