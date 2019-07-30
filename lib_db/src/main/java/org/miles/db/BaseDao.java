package org.miles.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Dao
public abstract class BaseDao<E> {

    @Insert
    public abstract long insert(E entity);

    @Insert
    public abstract long[] insert(List<E> entities);

    @Delete
    public abstract int delete(E entity);

    @Delete
    public abstract int delete(List<E> entities);

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
                "SELECT * FROM " + getTableName() + " where id=" + id
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
