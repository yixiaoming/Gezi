package org.miles.kaiyan.videolist;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.lib.data.kaiyan.KaiyanDataSource;
import org.miles.lib.data.kaiyan.api.KaiyanApi;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoItem;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoList;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KaiyanListFragmentModel extends ViewModel {

    private MutableLiveData<List<KaiyanVideoItem>> mKaiyanVideoDatas;
    private KaiyanApi mKaiyanApi;
    private long mCategoryId;

    public KaiyanListFragmentModel() {
        mKaiyanApi = KaiyanDataSource.api().kaiyanApi();
        mKaiyanVideoDatas = new MutableLiveData<>();
    }

    public MutableLiveData<List<KaiyanVideoItem>> getKaiyanVideoDatas() {
        return mKaiyanVideoDatas;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        mKaiyanApi.getVideoList(mCategoryId)
                .subscribeOn(Schedulers.io())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {

                    }
                })
                .subscribe(new Consumer<KaiyanVideoList>() {
                    @Override
                    public void accept(KaiyanVideoList kaiyanVideoList) {
                        mKaiyanVideoDatas.postValue(kaiyanVideoList.itemList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mKaiyanVideoDatas.postValue(null);
                    }
                });
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }
}
