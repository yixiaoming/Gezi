package org.miles.gank.today.todaylist;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.gank.common.Constrants;
import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;
import org.miles.gank.data.db.GankDao;
import org.miles.gank.data.entity.GankBaseEntity;
import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.lib.log.Logger;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankTodayListFragmentModel extends ViewModel {

    private static final int DEFAULT_COUNT = 20;
    private String mGankCategory;
    private GankApi mGankApi;
    private GankDao mGankDao;
    private MutableLiveData<List<GankTodayItemEntity>> mGankTodayItemEntities;

    public GankTodayListFragmentModel() {
        mGankTodayItemEntities = new MutableLiveData<>();
        mGankApi = GankDataSource.api().gankApi();
        mGankDao = GankDataSource.dao().gankDao();
    }

    public void setGankCategory(String category) {
        mGankCategory = category;
    }

    public MutableLiveData<List<GankTodayItemEntity>> getGankTodayItemEntities() {
        return mGankTodayItemEntities;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mGankDao.selectByCategory(mGankCategory)
                .subscribeOn(Schedulers.io())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankTodayItemEntities.postValue(null);
                    }
                })
                .doOnSuccess(new Consumer<List<GankTodayItemEntity>>() {
                    @Override
                    public void accept(List<GankTodayItemEntity> gankTodayItemEntities) throws Exception {
                        if (gankTodayItemEntities.size() > 0) {
                            mGankTodayItemEntities.postValue(gankTodayItemEntities);
                            return;
                        }
                        GankBaseEntity<List<GankTodayItemEntity>> items =
                                mGankApi.getTodayRandomItems(mGankCategory, 10).execute().body();
                        if (items == null) {
                            mGankTodayItemEntities.postValue(null);
                            Logger.d("gank query local data failed!");
                        }
                        processItems(items.results);
                        mGankDao.deleteByCategory(mGankCategory);
                        mGankDao.insert(items.results);
                        mGankTodayItemEntities.postValue(items.results);
                    }
                })
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public void refresh() {
        mGankApi.getTodayRandomItemsObservable(mGankCategory, DEFAULT_COUNT)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankTodayItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankTodayItemEntity>> listGankBaseEntity) throws Exception {
                        if (listGankBaseEntity.results.size() > 0) {
                            processItems(listGankBaseEntity.results);
                            mGankDao.deleteByCategory(mGankCategory);
                            mGankDao.insert(listGankBaseEntity.results);
                            mGankTodayItemEntities.getValue().addAll(0, listGankBaseEntity.results);
                        }
                        mGankTodayItemEntities.postValue(mGankTodayItemEntities.getValue());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankTodayItemEntities.postValue(mGankTodayItemEntities.getValue());
                    }
                });
    }

    private void processItems(List<GankTodayItemEntity> datas) {
        for (int i = datas.size() - 1; i >= 0; i--) {
            datas.get(i).category = mGankCategory;
            if (TextUtils.equals(datas.get(i).type, Constrants.CATEGORY_VIDEO)) {
                datas.remove(i);
            }
        }
    }
}
