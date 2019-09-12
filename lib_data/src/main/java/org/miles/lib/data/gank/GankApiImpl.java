package org.miles.lib.data.gank;

import org.miles.gank.data.GankDataSource;
import org.miles.lib.data.RetrofitManager;
import org.miles.gank.data.api.GankApi;

public class GankApiImpl implements GankDataSource.Api {
    @Override
    public GankApi gankApi() {
        return RetrofitManager.get().getGankApi();
    }
}
