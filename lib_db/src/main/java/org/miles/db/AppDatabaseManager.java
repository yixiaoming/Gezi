package org.miles.db;

import androidx.room.Room;

public class AppDatabaseManager {

    private AppDatabase mAppDatabase;

    private AppDatabaseManager() {
        mAppDatabase = Room.databaseBuilder(AppConfig.getContext(),
                AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public static AppDatabaseManager get() {
        return Holder.sInstance;
    }

    public UserDao getUserDao() {
        return mAppDatabase.userDao();
    }

    private static class Holder {
        public static final AppDatabaseManager sInstance = new AppDatabaseManager();
    }
}
