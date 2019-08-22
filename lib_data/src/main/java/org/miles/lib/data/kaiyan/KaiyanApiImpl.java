package org.miles.lib.data.kaiyan;

import org.miles.lib.data.RetrofitManager;
import org.miles.lib.data.kaiyan.api.KaiyanApi;

public class KaiyanApiImpl implements KaiyanDataSource.Api {
    @Override
    public KaiyanApi kaiyanApi() {
        return RetrofitManager.get().getKaiyanApi();
    }
}
