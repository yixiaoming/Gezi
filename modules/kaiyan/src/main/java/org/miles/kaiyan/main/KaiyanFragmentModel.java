package org.miles.kaiyan.main;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.kaiyan.data.api.KaiyanApi;
import org.miles.kaiyan.data.entity.KaiyanCategory;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KaiyanFragmentModel extends ViewModel {

    public static final String TAG = "KaiyanFragmentModel";

    private MutableLiveData<List<KaiyanCategory>> mKaiyanCategories;
    private KaiyanApi mKaiyanApi;

    public KaiyanFragmentModel() {
        mKaiyanApi = KaiyanDataSource.api().kaiyanApi();
        mKaiyanCategories = new MutableLiveData<>();
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mKaiyanApi.getCategories()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) {
                        mKaiyanCategories.postValue(kaiyanCategories);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mKaiyanCategories.postValue(null);
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