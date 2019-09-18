package org.miles.gank.xiandu.xiandulist;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;
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
    private MutableLiveData<List<GankCategoryItemEntity>> mGankEntities;
    private MutableLiveData<List<GankSecondCategoryEntity>> mSecondCategories;
    private HashMap<String, Integer> mCategoryPages;
    private String mCurDisplayCategory;

    public GankXianduListFragmentModel() {
        mGankApi = GankDataSource.api().gankApi();
        mGankEntities = new MutableLiveData<>();
        mSecondCategories = new MutableLiveData<>();
        mCategoryPages = new HashMap<>();
    }

    public MutableLiveData<List<GankCategoryItemEntity>> getGankEntities() {
        return mGankEntities;
    }

    public MutableLiveData<List<GankSecondCategoryEntity>> getSecondCategories() {
        return mSecondCategories;
    }

    @SuppressLint("CheckResult")
    public void initDatas(String categoryId) {
        mGankApi.getSecondCategories(categoryId)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankSecondCategoryEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankSecondCategoryEntity>> listGankBaseEntity) throws Exception {
                        mSecondCategories.postValue(listGankBaseEntity.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mSecondCategories.postValue(null);
                    }
                });
    }

    public void setCurDisplayCategory(String category) {
        mCurDisplayCategory = category;
    }

    @SuppressLint("CheckResult")
    public void loadCategoryList(final String categoryId) {
        if (mCategoryPages.get(categoryId) == null) {
            mCategoryPages.put(categoryId, DEFAULT_PAGE);
        }
        int page = mCategoryPages.get(categoryId);
        mGankApi.getCategoryItems(categoryId, DEFAULT_PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryItemEntity>> listGankBaseEntity) throws Exception {
                        mGankEntities.postValue(listGankBaseEntity.results);
                        mCategoryPages.put(categoryId, mCategoryPages.get(categoryId) + 1);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankEntities.postValue(null);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void refresh() {
        if (mCategoryPages.get(mCurDisplayCategory) == null) {
            mCategoryPages.put(mCurDisplayCategory, DEFAULT_PAGE);
        }
        final int page = mCategoryPages.get(mCurDisplayCategory);
        mGankApi.getCategoryItems(mCurDisplayCategory, DEFAULT_PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryItemEntity>> listGankBaseEntity) throws Exception {
                        if (listGankBaseEntity.results == null || listGankBaseEntity.results.size() == 0) {
                            Logger.d("load failed:" + mCurDisplayCategory + ",page:" + page);
                        } else {
                            mCategoryPages.put(mCurDisplayCategory, mCategoryPages.get(mCurDisplayCategory) + 1);
                        }
                        mGankEntities.getValue().addAll(0, listGankBaseEntity.results);
                        mGankEntities.postValue(mGankEntities.getValue());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankEntities.postValue(null);
                    }
                });
    }
}
