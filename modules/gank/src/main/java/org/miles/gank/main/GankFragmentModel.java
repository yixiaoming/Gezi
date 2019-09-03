package org.miles.gank.main;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankCategoryEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankFragmentModel extends ViewModel {

    private GankApi mGankApi;
    private MutableLiveData<List<GankCategoryEntity>> mGankCategories;

    public GankFragmentModel() {
        mGankApi = RetrofitManager.get().getGankApi();
        mGankCategories = new MutableLiveData<>();
    }

    public LiveData<List<GankCategoryEntity>> getGankCategoryes() {
        return mGankCategories;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mGankApi.getCategores()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryEntity>> listGankBaseEntity) throws Exception {
                        mGankCategories.postValue(listGankBaseEntity.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankCategories.postValue(null);
                    }
                });
    }
}
