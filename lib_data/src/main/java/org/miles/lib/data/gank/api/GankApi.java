package org.miles.lib.data.gank.api;


import org.miles.lib.data.gank.entity.GankBaseEntity;
import org.miles.lib.data.gank.entity.GankCategoryItemEntity;
import org.miles.lib.data.gank.entity.GankFirstCategoryEntity;
import org.miles.lib.data.gank.entity.GankSecondCategoryEntity;
import org.miles.lib.data.gank.entity.GankTodayItemEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {

    public static final String BASE_URL = "http://gank.io/api/";

    @GET("xiandu/categories")
    Observable<GankBaseEntity<List<GankFirstCategoryEntity>>> getFirstCategores();

    @GET("xiandu/category/{categoryId}")
    Observable<GankBaseEntity<List<GankSecondCategoryEntity>>> getSecondCategories(
            @Path("categoryId") String categoryId);

    @GET("xiandu/data/id/{id}/count/{count}/page/{page}")
    Observable<GankBaseEntity<List<GankCategoryItemEntity>>> getCategoryItems(
            @Path("id") String secondCategoryId,
            @Path("count") int count,
            @Path("page") int page
    );

    @GET("data/{type}/{count}/{page}")
    Observable<GankBaseEntity<List<GankTodayItemEntity>>> getTodayItems(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );

    @GET("random/data/{type}/{count}")
    Observable<GankBaseEntity<List<GankTodayItemEntity>>> getTodayRandomItems(
            @Path("type") String type,
            @Path("count") int count
    );
}
