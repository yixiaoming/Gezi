package org.miles.kaiyan.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.kaiyan.KaiyanDataSource;
import org.miles.lib.data.kaiyan.api.KaiyanApi;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KaiyanFragmentModel extends ViewModel {

    public static final String TAG = "KaiyanFragmentModel";

    private MutableLiveData<List<KaiyanCategory>> mKaiyanCategories;
    private KaiyanApi mKaiyanApi;

    public KaiyanFragmentModel() {
        mKaiyanCategories = new MutableLiveData<>();
        mKaiyanApi = KaiyanDataSource.api().kaiyanApi();
    }

    public void initDatas() {
        mKaiyanApi.getCategories()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) throws Exception {
                        mKaiyanCategories.postValue(kaiyanCategories);
                    }
                });
    }

    public LiveData<List<KaiyanCategory>> getKaiyanCategories() {
        if (mKaiyanCategories == null) {
            mKaiyanCategories = new MutableLiveData<>();
        }
        return mKaiyanCategories;
    }
}