package org.miles.kaiyan.videolist;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.kaiyan.data.api.KaiyanApi;
import org.miles.kaiyan.data.entity.KaiyanVideoItem;
import org.miles.kaiyan.data.entity.KaiyanVideoList;

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
                .subscribe(new Consumer<KaiyanVideoList>() {
                    @Override
                    public void accept(KaiyanVideoList kaiyanVideoList) {
                        for (int i = kaiyanVideoList.itemList.size() - 1; i >= 0; i--) {
                            KaiyanVideoItem item = kaiyanVideoList.itemList.get(i);
                            if (!isValid(item)) {
                                kaiyanVideoList.itemList.remove(item);
                            }
                        }
                        mKaiyanVideoDatas.postValue(kaiyanVideoList.itemList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mKaiyanVideoDatas.postValue(null);
                    }
                });
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }

    private boolean isValid(KaiyanVideoItem item) {
        return item != null && item.data != null && item.data.author != null
                && item.data.author.name != null && item.data.author.icon != null
                && item.data.cover != null;
    }
}
