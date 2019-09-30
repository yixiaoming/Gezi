package org.miles.gank.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.miles.gank.data.entity.GankCategoryItemEntity;
import org.miles.gank.data.entity.GankFirstCategoryEntity;
import org.miles.gank.data.entity.GankSecondCategoryEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public abstract class GankXianduDao {

    @Insert
    public abstract long[] insertFirstCategories(List<GankFirstCategoryEntity> firstCategoryEntities);

    @Insert
    public abstract long[] insertSecondCategories(List<GankSecondCategoryEntity> secondCategoryEntities);

    @Insert
    public abstract long[] insertGankItems(List<GankCategoryItemEntity> gankCategoryItemEntities);

    @Query("SELECT * FROM GankFirstCategoryEntity")
    public abstract Maybe<List<GankFirstCategoryEntity>> selectFirstCategoriesMaybe();

    @Query("SELECT * FROM GankSecondCategoryEntity WHERE categoryId=:arg0")
    public abstract Maybe<List<GankSecondCategoryEntity>> selectSecondCategoriesMaybe(String categoryId);

    @Query("SELECT * FROM GankCategoryItemEntity WHERE categoryId=:arg0")
    public abstract Maybe<List<GankCategoryItemEntity>> selectXianduItemByCategoryIdMaybe(String categoryId);

    @Query("DELETE FROM GankCategoryItemEntity where categoryId=:arg0")
    public abstract void deleteGankItemsByCategoryId(String categoryId);
}
