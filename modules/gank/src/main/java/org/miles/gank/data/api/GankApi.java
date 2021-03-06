package org.miles.gank.data.api;


import org.miles.gank.data.entity.GankBaseEntity;
import org.miles.gank.data.entity.GankCategoryItemEntity;
import org.miles.gank.data.entity.GankFirstCategoryEntity;
import org.miles.gank.data.entity.GankSecondCategoryEntity;
import org.miles.gank.data.entity.GankTodayItemEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {

    public static final String BASE_URL = "http://gank.io/api/";

    @GET("xiandu/categories")
    Observable<GankBaseEntity<List<GankFirstCategoryEntity>>> getFirstCategoresObservable();

    @GET("xiandu/categories")
    Call<GankBaseEntity<List<GankFirstCategoryEntity>>> getFirstCategores();

    @GET("xiandu/category/{categoryId}")
    Observable<GankBaseEntity<List<GankSecondCategoryEntity>>> getSecondCategoriesObservable(
            @Path("categoryId") String categoryId);

    @GET("xiandu/category/{categoryId}")
    Call<GankBaseEntity<List<GankSecondCategoryEntity>>> getSecondCategories(
            @Path("categoryId") String categoryId);

    @GET("xiandu/data/id/{id}/count/{count}/page/{page}")
    Observable<GankBaseEntity<List<GankCategoryItemEntity>>> getCategoryItemsObservable(
            @Path("id") String secondCategoryId,
            @Path("count") int count,
            @Path("page") int page
    );

    @GET("xiandu/data/id/{id}/count/{count}/page/{page}")
    Call<GankBaseEntity<List<GankCategoryItemEntity>>> getCategoryItems(
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
    Observable<GankBaseEntity<List<GankTodayItemEntity>>> getTodayRandomItemsObservable(
            @Path("type") String type,
            @Path("count") int count
    );

    @GET("random/data/{type}/{count}")
    Call<GankBaseEntity<List<GankTodayItemEntity>>> getTodayRandomItems(
            @Path("type") String type,
            @Path("count") int count
    );
}
