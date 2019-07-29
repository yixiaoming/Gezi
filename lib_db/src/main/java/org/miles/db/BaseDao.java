package org.miles.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Dao
public abstract class BaseDao<E> {

    @Insert
    public abstract long insert(E entity);

    @Delete
    public abstract int delete(E entity);

    @Update
    public abstract int update(E entity);

    public List<E> selectAll() {
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(
                "SELECT * FROM " + getTableName()
        );
        return doSelectList(query);
    }

    public E selectById(long id) {
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(
                "SELECT * FROM " + getTableName()
        );
        return doSelectOne(query);
    }

    public String getTableName() {
        Class clazz = (Class)
                ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        String tableName = clazz.getSimpleName();
        return tableName;
    }

    @RawQuery
    protected abstract List<E> doSelectList(SupportSQLiteQuery query);

    @RawQuery
    protected abstract E doSelectOne(SupportSQLiteQuery query);
}
