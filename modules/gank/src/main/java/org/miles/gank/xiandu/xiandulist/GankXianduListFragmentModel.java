package org.miles.gank.xiandu.xiandulist;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;
import org.miles.gank.data.db.GankXianduDao;
import org.miles.gank.data.entity.GankBaseEntity;
import org.miles.gank.data.entity.GankCategoryItemEntity;
import org.miles.gank.data.entity.GankSecondCategoryEntity;
import org.miles.lib.log.Logger;

import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankXianduListFragmentModel extends ViewModel {

    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_PAGE = 1;
    private GankApi mGankApi;
    private GankXianduDao mGankXianduDao;
    private MutableLiveData<List<GankCategoryItemEntity>> mGankEntities;
    private MutableLiveData<List<GankSecondCategoryEntity>> mSecondCategories;
    private HashMap<String, Integer> mCategoryPages = new HashMap<>();
    private String mCurSecondCategoryId;

    public GankXianduListFragmentModel() {
        mGankApi = GankDataSource.api().gankApi();
        mGankXianduDao = GankDataSource.dao().gankXianduDao();
        mGankEntities = new MutableLiveData<>();
        mSecondCategories = new MutableLiveData<>();
    }

    public MutableLiveData<List<GankCategoryItemEntity>> getGankEntities() {
        return mGankEntities;
    }

    public MutableLiveData<List<GankSecondCategoryEntity>> getSecondCategories() {
        return mSecondCategories;
    }

    @SuppressLint("CheckResult")
    public void initDatas(final String firstCategoryId) {
        mGankXianduDao.selectSecondCategoriesMaybe(firstCategoryId)
                .doOnSuccess(new Consumer<List<GankSecondCategoryEntity>>() {
                    @Override
                    public void accept(List<GankSecondCategoryEntity> entities) throws Exception {
                        if (entities != null && entities.size() > 0) {
                            mSecondCategories.postValue(entities);
                            return;
                        }
                        GankBaseEntity<List<GankSecondCategoryEntity>> body =
                                mGankApi.getSecondCategories(firstCategoryId).execute().body();
                        if (body != null) {
                            entities = body.results;
                            for (GankSecondCategoryEntity entity : entities) {
                                entity.firstCategoryId = firstCategoryId;
                            }
                            mGankXianduDao.deleteSecondCategoryByCategoryId(firstCategoryId);
                            mGankXianduDao.insertSecondCategories(entities);
                            mSecondCategories.postValue(entities);
                        } else {
                            mSecondCategories.postValue(null);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public void loadCategoryList(final String secondItemId) {
        mCurSecondCategoryId = secondItemId;
        if (mCategoryPages.get(secondItemId) == null) {
            mCategoryPages.put(secondItemId, DEFAULT_PAGE);
        }
        final int page = mCategoryPages.get(secondItemId);

        mGankXianduDao.selectXianduItemByCategoryIdMaybe(secondItemId)
                .doOnSuccess(new Consumer<List<GankCategoryItemEntity>>() {
                    @Override
                    public void accept(List<GankCategoryItemEntity> gankCategoryItemEntities) throws Exception {
                        if (gankCategoryItemEntities != null && gankCategoryItemEntities.size() > 0) {
                            mGankEntities.postValue(gankCategoryItemEntities);
                            mCategoryPages.put(secondItemId, mCategoryPages.get(secondItemId) + 1);
                            return;
                        }
                        GankBaseEntity<List<GankCategoryItemEntity>> body =
                                mGankApi.getCategoryItems(secondItemId, DEFAULT_PAGE_SIZE, page).execute().body();
                        if (body != null) {
                            gankCategoryItemEntities = body.results;
                            for (GankCategoryItemEntity entity : gankCategoryItemEntities) {
                                entity.categoryId = secondItemId;
                            }
                            mGankXianduDao.deleteGankItemsByCategoryId(secondItemId);
                            mGankXianduDao.insertGankItems(gankCategoryItemEntities);
                            mGankEntities.postValue(gankCategoryItemEntities);
                        } else {
                            mGankEntities.postValue(null);
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankEntities.postValue(null);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public void refresh() {
        if (mCategoryPages.get(mCurSecondCategoryId) == null) {
            mCategoryPages.put(mCurSecondCategoryId, DEFAULT_PAGE);
        }
        final int page = mCategoryPages.get(mCurSecondCategoryId);
        mGankApi.getCategoryItemsObservable(mCurSecondCategoryId, DEFAULT_PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryItemEntity>> listGankBaseEntity) throws Exception {
                        if (listGankBaseEntity.results == null || listGankBaseEntity.results.size() == 0) {
                            Logger.d("load failed:" + mCurSecondCategoryId + ",page:" + page);
                        } else {
                            for (GankCategoryItemEntity entity : listGankBaseEntity.results) {
                                entity.categoryId = mCurSecondCategoryId;
                            }
                            mGankXianduDao.deleteGankItemsByCategoryId(mCurSecondCategoryId);
                            mGankXianduDao.insertGankItems(listGankBaseEntity.results);
                            mCategoryPages.put(mCurSecondCategoryId, mCategoryPages.get(mCurSecondCategoryId) + 1);
                        }
                        mGankEntities.getValue().addAll(0, listGankBaseEntity.results);
                        mGankEntities.postValue(mGankEntities.getValue());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankEntities.postValue(mGankEntities.getValue());
                    }
                });
    }
}
