package org.miles.lib.data.gank.db;

import androidx.room.Dao;

import org.miles.lib.data.gank.entity.GankEntity;
import org.miles.lib.room.BaseDao;

@Dao
public abstract class GankDao extends BaseDao<GankEntity> {
}
