package org.miles.kaiyan.api;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankCategoryEntity;
import org.miles.lib.data.gank.entity.GankEntity;

import java.io.IOException;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class GankApiTest {

    private GankApi mGankApi;

    @Before
    public void init() {
        mGankApi = RetrofitManager.get().getGankApi();
    }

    @Test
    public void getCategores() {
        mGankApi.getCategores()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankCategoryEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankCategoryEntity>> listGankBaseEntity) {
                        for (GankCategoryEntity entity : listGankBaseEntity.results) {
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
        mGankApi.getContents("wow", 10, 1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankEntity>> listGankBaseEntity) {
                        for (GankEntity entity : listGankBaseEntity.results) {
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
        mGankApi.getRandomContents("App", 10)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GankBaseEntity<List<GankEntity>>>() {
                    @Override
                    public void accept(GankBaseEntity<List<GankEntity>> listGankBaseEntity) throws Exception {
                        for (GankEntity entity : listGankBaseEntity.results) {
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
