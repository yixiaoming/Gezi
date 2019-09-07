package org.miles.gank.today;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;

import java.util.Arrays;
import java.util.List;

public class GankTodayFragmentModel extends ViewModel {

    private static String[] DEFAULT_CATEGORIES = {
            "all", "福利", "Android", "iOS", "休息视频", "瞎推荐", "拓展资源", "前端",
    };
    private GankApi mGankApi;
    private MutableLiveData<List<String>> mCategories;

    public GankTodayFragmentModel() {
        mCategories = new MutableLiveData<>();
        mGankApi = RetrofitManager.get().getGankApi();
    }

    public MutableLiveData<List<String>> getCategories() {
        return mCategories;
    }

    public void initDatas() {
        mCategories.postValue(Arrays.asList(DEFAULT_CATEGORIES));
    }
}
