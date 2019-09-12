package org.miles.kaiyan.data.api;

import org.miles.kaiyan.data.entity.KaiyanCategory;
import org.miles.kaiyan.data.entity.KaiyanVideoList;

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
            @Query("id") long id
    );

    @GET("api/v3/categories/videoList")
    Observable<KaiyanVideoList> getVideoList(
            @Query("id") long id,
            @Query("start") int start,
            @Query("num") int num
    );
}
