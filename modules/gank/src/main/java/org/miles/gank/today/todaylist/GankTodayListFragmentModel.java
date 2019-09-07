package org.miles.gank.today.todaylist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankTodayItemEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankTodayListFragmentModel extends ViewModel {

    private static final int DEFAULT_COUNT = 20;
    private GankApi mGankApi;
    private MutableLiveData<List<GankTodayItemEntity>> mGankTodayItemEntities;

    public GankTodayListFragmentModel() {
        mGankTodayItemEntities = new MutableLiveData<>();
        mGankApi = RetrofitManager.get().getGankApi();
    }

    public MutableLiveData<List<GankTodayItemEntity>> getGankTodayItemEntities() {
        return mGankTodayItemEntities;
    }

    public void initDatas(String category) {
        mGankApi.getTodayRandomItems(category, DEFAULT_COUNT)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankTodayItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankTodayItemEntity>> listGankBaseEntity) throws Exception {
                        mGankTodayItemEntities.postValue(listGankBaseEntity.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankTodayItemEntities.postValue(null);
                    }
                });
    }
}
