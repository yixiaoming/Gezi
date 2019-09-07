package org.miles.gank.category.categorylist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankCategoryItemEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankCategoryListFragmentModel extends ViewModel {

    public static final int DEFAULT_PAGE_SIZE = 20;
    private GankApi mGankApi;
    private MutableLiveData<List<GankCategoryItemEntity>> mGankEntities;

    public GankCategoryListFragmentModel() {
        mGankApi = RetrofitManager.get().getGankApi();
    }

    public MutableLiveData<List<GankCategoryItemEntity>> getGankEntities() {
        if (mGankEntities == null) {
            mGankEntities = new MutableLiveData<>();
        }
        return mGankEntities;
    }

    public void initDatas(String categoryId, int page) {
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
