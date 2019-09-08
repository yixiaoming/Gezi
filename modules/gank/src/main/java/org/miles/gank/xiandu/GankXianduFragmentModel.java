package org.miles.gank.xiandu;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankFirstCategoryEntity;
import org.miles.lib.data.gank.entity.GankSecondCategoryEntity;
import org.miles.lib.log.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class GankXianduFragmentModel extends ViewModel {

    private GankApi mGankApi;
    private MutableLiveData<List<GankSecondCategoryEntity>> mGankCategories;

    public GankXianduFragmentModel() {
        mGankApi = RetrofitManager.get().getGankApi();
        mGankCategories = new MutableLiveData<>();
    }

    public LiveData<List<GankSecondCategoryEntity>> getGankCategoryes() {
        return mGankCategories;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mGankApi.getFirstCategores()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<GankBaseEntity<List<GankFirstCategoryEntity>>,
                        List<GankSecondCategoryEntity>>() {
                    @Override
                    public List<GankSecondCategoryEntity> apply(
                            GankBaseEntity<List<GankFirstCategoryEntity>> listGankBaseEntity
                    ) throws Exception {
                        List<GankSecondCategoryEntity> secondCategoryEntities = new ArrayList<>();
                        for (GankFirstCategoryEntity entity : listGankBaseEntity.results) {
                            Response<GankBaseEntity<List<GankSecondCategoryEntity>>> response =
                                    mGankApi.getSecondCategoriesSync(entity.enName).execute();
                            secondCategoryEntities.addAll(response.body().results);
                        }
                        return secondCategoryEntities;
                    }
                })
                .subscribe(new Consumer<List<GankSecondCategoryEntity>>() {
                    @Override
                    public void accept(List<GankSecondCategoryEntity> gankSecondCategoryEntities) throws Exception {
                        mGankCategories.postValue(gankSecondCategoryEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable);
                    }
                });
    }
}
