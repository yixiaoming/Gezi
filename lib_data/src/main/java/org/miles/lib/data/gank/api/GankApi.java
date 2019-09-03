package org.miles.lib.data.gank.api;


import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankCategoryEntity;
import org.miles.lib.data.gank.entity.GankEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {

    public static final String BASE_URL = "http://gank.io/api/";

    @GET("xiandu/categories")
    Observable<GankBaseEntity<List<GankCategoryEntity>>> getCategores();

    @GET("data/{type}/{count}/{page}")
    Observable<GankBaseEntity<List<GankEntity>>> getContents(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );

    @GET("random/data/{type}/{count}")
    Observable<GankBaseEntity<List<GankEntity>>> getRandomContents(
            @Path("type") String type,
            @Path("count") int count
    );
}
