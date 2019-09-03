package org.miles.lib.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Dao
public abstract class BaseDao<E> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(E entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insert(List<E> entities);

    @Delete
    public abstract int delete(E entity);

    @Transaction
    public int delete(long id) {
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(
                "DELETE FROM " + getTableName() + " WHERE id=?",
                new Object[]{id}
        );
        return doDeleteOne(query);
    }

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
                "SELECT * FROM " + getTableName() + " where id=?",
                new Object[]{id}
        );
        return doSelectOne(query);
    }

    public List<E> selectByPage(int page, int pageSize) {
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(
                "SELECT * FROM " + getTableName() + " LIMIT (?,?)",
                new Object[]{(page - 1) * pageSize, pageSize}
        );
        return doSelectList(query);
    }

    protected String getTableName() {
        Class clazz = (Class)
                ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        return clazz.getSimpleName();
    }

    @RawQuery
    protected abstract List<E> doSelectList(SupportSQLiteQuery query);

    @RawQuery
    protected abstract E doSelectOne(SupportSQLiteQuery query);

    @RawQuery
    protected abstract int doDeleteOne(SupportSQLiteQuery query);
}
