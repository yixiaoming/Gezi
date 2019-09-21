package org.miles.gank.today;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.gank.data.GankDataSource;
import org.miles.gank.data.api.GankApi;

import java.util.Arrays;
import java.util.List;

import static org.miles.gank.common.Constrants.DEFAULT_CATEGORIES;

public class GankTodayFragmentModel extends ViewModel {


    private GankApi mGankApi;
    private MutableLiveData<List<String>> mCategories;

    public GankTodayFragmentModel() {
        mCategories = new MutableLiveData<>();
        mGankApi = GankDataSource.api().gankApi();
    }

    public MutableLiveData<List<String>> getCategories() {
        return mCategories;
    }

    public void initDatas() {
        mCategories.postValue(Arrays.asList(DEFAULT_CATEGORIES));
    }
}
