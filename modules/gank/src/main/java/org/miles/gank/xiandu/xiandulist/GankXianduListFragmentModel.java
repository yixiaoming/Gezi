package org.miles.gank.xiandu.xiandulist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankCategoryItemEntity;
import org.miles.lib.data.gank.entity.GankSecondCategoryEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankXianduListFragmentModel extends ViewModel {

    public static final int DEFAULT_PAGE_SIZE = 20;
    private GankApi mGankApi;
    private MutableLiveData<List<GankCategoryItemEntity>> mGankEntities;
    private MutableLiveData<List<GankSecondCategoryEntity>> mSecondCategories;

    public GankXianduListFragmentModel() {
        mGankApi = RetrofitManager.get().getGankApi();
        if (mGankEntities == null) {
            mGankEntities = new MutableLiveData<>();
        }
        if (mSecondCategories == null) {
            mSecondCategories = new MutableLiveData<>();
        }
    }

    public MutableLiveData<List<GankCategoryItemEntity>> getGankEntities() {
        return mGankEntities;
    }

    public MutableLiveData<List<GankSecondCategoryEntity>> getSecondCategories() {
        return mSecondCategories;
    }

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

    public void loadCategoryList(String categoryId, int page) {
        mGankApi.getCategoryItems(categoryId, DEFAULT_PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryItemEntity>> listGankBaseEntity) throws Exception {
                        mGankEntities.postValue(listGankBaseEntity.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGankEntities.postValue(null);
                    }
                });
    }
}
