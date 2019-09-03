package org.miles.lib.data;

import org.miles.lib.data.gank.api.GankApi;
import org.miles.lib.data.kaiyan.api.KaiyanApi;
import org.miles.lib.log.Logger;

import java.io.IOException;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static final String TAG = "RetrofitManager";

    public static final String UA_KEY = "User-Agent";
    public static final String UA_VALUE = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)";

    private static volatile RetrofitManager sInstance;
    private OkHttpClient mOkHttpClient;

    private KaiyanApi mKaiyanApi;
    private GankApi mGankApi;

    private RetrofitManager() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(TAG, "accept: " + throwable);
            }
        });
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    .removeHeader(UA_KEY)
                                    .addHeader(UA_KEY, UA_VALUE)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();
        }
    }

    public static RetrofitManager get() {
        if (sInstance == null) {
            synchronized (RetrofitManager.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitManager();
                }
            }
        }
        return sInstance;
    }

    public KaiyanApi getKaiyanApi() {
        if (mKaiyanApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(KaiyanApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
            mKaiyanApi = retrofit.create(KaiyanApi.class);
        }
        return mKaiyanApi;
    }

    public GankApi getGankApi() {
        if (mGankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GankApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
            mGankApi = retrofit.create(GankApi.class);
        }
        return mGankApi;
    }
}
