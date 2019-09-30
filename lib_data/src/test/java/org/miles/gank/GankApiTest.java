package org.miles.gank;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miles.lib.data.RetrofitManager;
import org.miles.gank.data.api.GankApi;
import org.miles.gank.data.entity.GankBaseEntity;
import org.miles.gank.data.entity.GankCategoryItemEntity;
import org.miles.gank.data.entity.GankFirstCategoryEntity;
import org.miles.gank.data.entity.GankSecondCategoryEntity;
import org.miles.gank.data.entity.GankTodayItemEntity;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)
public class GankApiTest {

    private GankApi mGankApi;

    @Before
    public void init() {
        mGankApi = RetrofitManager.get().getGankApi();
    }

    @Test
    public void getFirstCategores() {
        mGankApi.getFirstCategoresObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankFirstCategoryEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankFirstCategoryEntity>> listGankBaseEntity) {
                        for (GankFirstCategoryEntity entity : listGankBaseEntity.results) {
                            System.out.println(entity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable);
                    }
                });
    }

    @Test
    public void getSecondCategories() {
        mGankApi.getSecondCategoriesObservable("apps")
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankSecondCategoryEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankSecondCategoryEntity>> listGankBaseEntity) throws Exception {
                        for (GankSecondCategoryEntity entity : listGankBaseEntity.results) {
                            System.out.println(entity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable);
                    }
                });

    }

    @Test
    public void getCategoryItems() {
        mGankApi.getCategoryItemsObservable("zhihu", 10, 1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryItemEntity>> listGankBaseEntity) throws Exception {
                        for (GankCategoryItemEntity entity : listGankBaseEntity.results) {
                            System.out.println(entity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable);
                    }
                });
    }

    @Test
    public void getContents() {
        mGankApi.getTodayItems("wow", 10, 1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankTodayItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankTodayItemEntity>> listGankBaseEntity) {
                        for (GankTodayItemEntity entity : listGankBaseEntity.results) {
                            System.out.println(entity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        System.out.println(throwable);
                    }
                });
    }

    @Test
    public void getRandomContents() {
        mGankApi.getTodayRandomItemsObservable("App", 10)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankTodayItemEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankTodayItemEntity>> listGankBaseEntity) throws Exception {
                        for (GankTodayItemEntity entity : listGankBaseEntity.results) {
                            System.out.println(entity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable);
                    }
                });
    }


}
