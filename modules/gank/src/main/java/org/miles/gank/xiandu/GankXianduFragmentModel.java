package org.miles.gank.xiandu;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;
import org.miles.gank.data.db.GankXianduDao;
import org.miles.gank.data.entity.GankBaseEntity;
import org.miles.gank.data.entity.GankFirstCategoryEntity;
import org.miles.gank.data.entity.GankSecondCategoryEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankXianduFragmentModel extends ViewModel {

    private GankApi mGankApi;
    private GankXianduDao mGankXianduDao;
    private MutableLiveData<List<GankSecondCategoryEntity>> mGankCategories;
    private MutableLiveData<List<GankFirstCategoryEntity>> mGankFirstCategories;

    public GankXianduFragmentModel() {
        mGankApi = GankDataSource.api().gankApi();
        mGankXianduDao = GankDataSource.dao().gankXianduDao();
        mGankCategories = new MutableLiveData<>();
        mGankFirstCategories = new MutableLiveData<>();
    }

    public LiveData<List<GankSecondCategoryEntity>> getGankCategoryes() {
        return mGankCategories;
    }

    public MutableLiveData<List<GankFirstCategoryEntity>> getGankFirstCategories() {
        return mGankFirstCategories;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mGankXianduDao.selectFirstCategoriesMaybe()
                .doOnSuccess(new Consumer<List<GankFirstCategoryEntity>>() {
                    @Override
                    public void accept(List<GankFirstCategoryEntity> gankFirstCategoryEntities) throws Exception {
                        if (gankFirstCategoryEntities != null && gankFirstCategoryEntities.size() > 0) {
                            mGankFirstCategories.postValue(gankFirstCategoryEntities);
                            return;
                        }
                        GankBaseEntity<List<GankFirstCategoryEntity>> body = mGankApi.getFirstCategores().execute().body();
                        if (body != null) {
                            gankFirstCategoryEntities = body.results;
                            mGankXianduDao.insertFirstCategories(gankFirstCategoryEntities);
                            mGankFirstCategories.postValue(gankFirstCategoryEntities);
                        } else {
                            mGankFirstCategories.postValue(null);
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankFirstCategories.postValue(null);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
