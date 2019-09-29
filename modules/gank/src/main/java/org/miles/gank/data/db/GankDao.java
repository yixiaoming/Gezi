package org.miles.gank.data.db;

import androidx.room.Dao;
import androidx.room.Query;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.lib.room.BaseDao;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public abstract class GankDao extends BaseDao<GankTodayItemEntity> {

    @Query("SELECT * FROM ganktodayitementity WHERE category=:arg0")
    public abstract Maybe<List<GankTodayItemEntity>> selectByCategory(String category);

    @Query("DELETE FROM ganktodayitementity WHERE category=:arg0")
    public abstract void deleteByCategory(String category);
}
