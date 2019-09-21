package org.miles.gank.today.todaylist;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.gank.common.Constrants;
import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;
import org.miles.gank.data.entity.GankBaseEntity;
import org.miles.gank.data.entity.GankTodayItemEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankTodayListFragmentModel extends ViewModel {

    private static final int DEFAULT_COUNT = 20;
    private GankApi mGankApi;
    private MutableLiveData<List<GankTodayItemEntity>> mGankTodayItemEntities;

    public GankTodayListFragmentModel() {
        mGankTodayItemEntities = new MutableLiveData<>();
        mGankApi = GankDataSource.api().gankApi();
    }

    public MutableLiveData<List<GankTodayItemEntity>> getGankTodayItemEntities() {
        return mGankTodayItemEntities;
    }

    @SuppressLint("CheckResult")
    public void initDatas(String category) {
        mGankApi.getTodayRandomItems(category, DEFAULT_COUNT)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankTodayItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankTodayItemEntity>> listGankBaseEntity) throws Exception {
                        removeVideoItem(listGankBaseEntity.results);
                        mGankTodayItemEntities.postValue(listGankBaseEntity.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankTodayItemEntities.postValue(null);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void refresh(String gankCategory) {
        mGankApi.getTodayRandomItems(gankCategory, DEFAULT_COUNT)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankTodayItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankTodayItemEntity>> listGankBaseEntity) throws Exception {
                        removeVideoItem(listGankBaseEntity.results);
                        mGankTodayItemEntities.getValue().addAll(0, listGankBaseEntity.results);
                        mGankTodayItemEntities.postValue(mGankTodayItemEntities.getValue());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankTodayItemEntities.postValue(null);
                    }
                });
    }

    private void removeVideoItem(List<GankTodayItemEntity> datas) {
        for (int i = datas.size() - 1; i >= 0; i--) {
            if (TextUtils.equals(datas.get(i).type, Constrants.CATEGORY_VIDEO)) {
                datas.remove(i);
            }
        }
    }
}
