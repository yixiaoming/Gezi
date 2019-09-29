package org.miles.kaiyan.videolist;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.kaiyan.data.api.KaiyanApi;
import org.miles.kaiyan.data.db.KaiyanVideoItemDao;
import org.miles.kaiyan.data.entity.KaiyanVideoItem;
import org.miles.kaiyan.data.entity.KaiyanVideoList;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class KaiyanListFragmentModel extends ViewModel {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private MutableLiveData<List<KaiyanVideoItem>> mKaiyanVideoDatas;
    private KaiyanApi mKaiyanApi;
    private KaiyanVideoItemDao mKaiyanVideoItemDao;
    private long mCategoryId;
    private int mPageStart = 1;

    public KaiyanListFragmentModel() {
        mKaiyanApi = KaiyanDataSource.api().kaiyanApi();
        mKaiyanVideoItemDao = KaiyanDataSource.dao().kaiyanVideoItemDao();
        mKaiyanVideoDatas = new MutableLiveData<>();
    }

    public MutableLiveData<List<KaiyanVideoItem>> getKaiyanVideoDatas() {
        return mKaiyanVideoDatas;
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        Observable
                .create(new ObservableOnSubscribe<List<KaiyanVideoItem>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<KaiyanVideoItem>> emitter) throws Exception {
                        emitter.onNext(mKaiyanVideoItemDao.selectByTabIdPage(mCategoryId, DEFAULT_PAGE_SIZE, 0));
                    }
                })
                .doOnNext(new Consumer<List<KaiyanVideoItem>>() {
                    @Override
                    public void accept(List<KaiyanVideoItem> kaiyanVideoItems) throws Exception {
                        if (kaiyanVideoItems == null || kaiyanVideoItems.size() == 0) {
                            KaiyanVideoList kaiyanVideoList = mKaiyanApi.getVideoList(mCategoryId).execute().body();
                            if (kaiyanVideoList == null) return;
                            processData(kaiyanVideoList);
                            mKaiyanVideoItemDao.deleteByCategoryId(mCategoryId);
                            mKaiyanVideoItemDao.insert(kaiyanVideoList.itemList);
                            mKaiyanVideoDatas.postValue(kaiyanVideoList.itemList);
                            mKaiyanVideoDatas.postValue(kaiyanVideoList.itemList);
                        }
                        mKaiyanVideoDatas.postValue(kaiyanVideoItems);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }

    private boolean isValid(KaiyanVideoItem item) {
        return item != null && item.data != null && item.data.author != null
                && item.data.author.name != null && item.data.author.icon != null
                && item.data.cover != null;
    }

    @SuppressLint("CheckResult")
    public void doRefresh() {
        mKaiyanApi.getVideoList(mCategoryId, mPageStart * DEFAULT_PAGE_SIZE, DEFAULT_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<KaiyanVideoList>() {
                    @Override
                    public void accept(KaiyanVideoList kaiyanVideoList) {
                        if (kaiyanVideoList==null) return;
                        processData(kaiyanVideoList);
                        mPageStart++;
                        mKaiyanVideoItemDao.deleteByCategoryId(mCategoryId);
                        mKaiyanVideoItemDao.insert(kaiyanVideoList.itemList);
                        mKaiyanVideoDatas.getValue().addAll(0, kaiyanVideoList.itemList);
                        mKaiyanVideoDatas.postValue(mKaiyanVideoDatas.getValue());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mKaiyanVideoDatas.postValue(mKaiyanVideoDatas.getValue());
                    }
                });
    }

    private void processData(KaiyanVideoList kaiyanVideoList) {
        for (int i = kaiyanVideoList.itemList.size() - 1; i >= 0; i--) {
            KaiyanVideoItem item = kaiyanVideoList.itemList.get(i);
            if (!isValid(item)) {
                kaiyanVideoList.itemList.remove(item);
            }
            item.categoryId = mCategoryId;
        }
    }
}
