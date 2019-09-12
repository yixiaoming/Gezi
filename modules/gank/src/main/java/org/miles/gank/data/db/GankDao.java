package org.miles.gank.data.db;

import androidx.room.Dao;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.lib.room.BaseDao;

@Dao
public abstract class GankDao extends BaseDao<GankTodayItemEntity> {
}
