package org.miles.lib.data.kaiyan;

import org.miles.kaiyan.data.KaiyanDataSource;
import org.miles.lib.data.RetrofitManager;
import org.miles.kaiyan.data.api.KaiyanApi;

public class KaiyanApiImpl implements KaiyanDataSource.Api {
    @Override
    public KaiyanApi kaiyanApi() {
        return RetrofitManager.get().getKaiyanApi();
    }
}
