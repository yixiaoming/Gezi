package org.miles.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "gezi.db";

    abstract UserDao userDao();
}