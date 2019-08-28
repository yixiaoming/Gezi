package org.miles.kaiyan.videolist;

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
        mKaiyanVideoDatas = new MutableLiveData<>();
        mKaiyanApi = KaiyanDataSource.api().kaiyanApi();
    }

    public MutableLiveData<List<KaiyanVideoItem>> getKaiyanVideoDatas() {
        return mKaiyanVideoDatas;
    }

    public void initDatas() {
        mKaiyanApi.getVideoList(mCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<KaiyanVideoList>() {
                    @Override
                    public void accept(KaiyanVideoList kaiyanVideoList) throws Exception {
                        mKaiyanVideoDatas.postValue(kaiyanVideoList.itemList);
                    }
                })
                .subscribe();
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }
}
