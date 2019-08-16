package org.miles.lib.data.kaiyan.db;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miles.lib.data.AppDatabase;
import org.miles.lib.data.kaiyan.api.KaiyanApi;
import org.miles.lib.data.kaiyan.api.RetrofitManager;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;

import java.util.List;

import io.reactivex.functions.Consumer;

@RunWith(AndroidJUnit4.class)
public class KaiyanCategoryDaoTest {

    private KaiyanCategoryDao mKaiyanCategoryDao;
    private KaiyanApi mKaiyanApi;

    @Before
    public void init() {
        Context context = ApplicationProvider.getApplicationContext();
        mKaiyanCategoryDao = AppDatabase.getAllowMainThread(context)
                .kaiyanCategoryDao();
        mKaiyanApi = RetrofitManager.get().getKaiyanApi();
    }

    @After
    public void clear() {
        AppDatabase.get().clear();
    }

    @Test
    public void insert() {
        mKaiyanApi.getCategories()
                .subscribe(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) throws Exception {
                        mKaiyanCategoryDao.insert(kaiyanCategories);

                        List<KaiyanCategory> list = mKaiyanCategoryDao.selectAll();
                        for (KaiyanCategory category : list) {
                            System.out.println(category);
                        }
                    }
                });
    }
}