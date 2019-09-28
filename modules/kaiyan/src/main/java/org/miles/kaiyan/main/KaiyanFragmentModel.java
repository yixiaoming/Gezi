package org.miles.kaiyan.main;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.kaiyan.data.api.KaiyanApi;
import org.miles.kaiyan.data.db.KaiyanCategoryDao;
import org.miles.kaiyan.data.entity.KaiyanCategory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KaiyanFragmentModel extends ViewModel {

    public static final String TAG = "KaiyanFragmentModel";

    private MutableLiveData<List<KaiyanCategory>> mKaiyanCategories;
    private KaiyanApi mKaiyanApi;
    private KaiyanCategoryDao mKaiyanCategoryDao;

    public KaiyanFragmentModel() {
        mKaiyanApi = KaiyanDataSource.api().kaiyanApi();
        mKaiyanCategoryDao = KaiyanDataSource.dao().kaiyanCategoryDao();
        mKaiyanCategories = new MutableLiveData<>();
    }

    @SuppressLint("CheckResult")
    public void initDatas() {
        Observable
                .create(new ObservableOnSubscribe<List<KaiyanCategory>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<KaiyanCategory>> emitter) throws Exception {
                        emitter.onNext(mKaiyanCategoryDao.selectAll());
                    }
                })
                .doOnNext(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) throws Exception {
                        if (kaiyanCategories == null || kaiyanCategories.size() == 0) {
                            kaiyanCategories = mKaiyanApi.getCategoryies().execute().body();
                            mKaiyanCategoryDao.insert(kaiyanCategories);
                        }
                        mKaiyanCategories.postValue(kaiyanCategories);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mKaiyanCategories.postValue(null);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public LiveData<List<KaiyanCategory>> getKaiyanCategories() {
        if (mKaiyanCategories == null) {
            mKaiyanCategories = new MutableLiveData<>();
        }
        return mKaiyanCategories;
    }
}