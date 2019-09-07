package org.miles.gank.category;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;

import java.util.Arrays;
import java.util.List;

public class GankCategoryFragmentModel extends ViewModel {

    private GankApi mGankApi;
    //    private MutableLiveData<List<GankCategoryEntity>> mGankCategories;
    private MutableLiveData<List<String>> mGankCategories;

    private static String[] DEFAULT_CATEGORIES =
            new String[]{"福利", "Android", "iOS", "休息视频", "拓展资源", "前端", "all"};

    public GankCategoryFragmentModel() {
        mGankApi = RetrofitManager.get().getGankApi();
        mGankCategories = new MutableLiveData<>();
    }

    public LiveData<List<String>> getGankCategoryes() {
        return mGankCategories;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mGankCategories.postValue(Arrays.asList(DEFAULT_CATEGORIES));
    }
}
