package org.miles.lib.data.kaiyan.api;

import org.miles.lib.data.kaiyan.entity.KaiyanCategory;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KaiyanApi {

    public static final String BASE_URL = "http://baobab.kaiyanapp.com/";

    @GET("api/v4/categories/")
    Observable<List<KaiyanCategory>> getCategories();

    @GET("api/v3/categories/videoList")
    Observable<KaiyanVideoList> getVideoList(
            @Query("id") int id
    );

    @GET("api/v3/categories/videoList")
    Observable<KaiyanVideoList> getVideoList(
            @Query("id") int id,
            @Query("start") int start,
            @Query("num") int num
    );
}