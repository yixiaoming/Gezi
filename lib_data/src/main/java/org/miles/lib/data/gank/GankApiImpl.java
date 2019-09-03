package org.miles.lib.data.gank;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.gank.api.GankApi;

public class GankApiImpl implements GankDataSource.Api {
    @Override
    public GankApi gankApi() {
        return RetrofitManager.get().getGankApi();
    }
}
