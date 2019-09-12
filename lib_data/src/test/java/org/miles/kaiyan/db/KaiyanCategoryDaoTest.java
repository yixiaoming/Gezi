package org.miles.kaiyan.db;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miles.lib.data.kaiyan.KaiyanDataSource;
import org.miles.kaiyan.data.api.KaiyanApi;
import org.miles.kaiyan.data.db.KaiyanCategoryDao;
import org.miles.kaiyan.data.entity.KaiyanCategory;
import org.miles.lib.data.AppDatabase;
import org.miles.lib.data.DataManager;
import org.miles.lib.data.RetrofitManager;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)
public class KaiyanCategoryDaoTest {

    private KaiyanCategoryDao mKaiyanCategoryDao;
    private KaiyanApi mKaiyanApi;

    @Before
    public void init() {
        Context context = ApplicationProvider.getApplicationContext();
        DataManager.init(context);
        mKaiyanCategoryDao = KaiyanDataSource.dao().kaiyanCategoryDao();
        mKaiyanApi = RetrofitManager.get().getKaiyanApi();
    }

    @After
    public void clear() {
        AppDatabase.get().clear();
    }

    @Test
    public void insert() {
        mKaiyanApi.getCategories()
                .doOnNext(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) throws Exception {
                        mKaiyanCategoryDao.insert(kaiyanCategories);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) throws Exception {
                        List<KaiyanCategory> list = mKaiyanCategoryDao.selectAll();
                        Assert.assertNotEquals(list.size(), 0);
                        for (KaiyanCategory category : list) {
                            System.out.println(category);
                        }
                    }
                });
    }
}