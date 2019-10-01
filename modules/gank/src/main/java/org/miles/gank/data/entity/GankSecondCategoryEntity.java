package org.miles.gank.data.entity;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class GankSecondCategoryEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = BaseColumns._ID)
    @SerializedName("_id")
    public String id;

    @SerializedName("create_at")
    public String createAt;

    public String icon;

    public String firstCategoryId;

    @SerializedName("id")
    public String categoryId;

    public String title;

    @Override
    public String toString() {
        return "GankSecondCategoryEntity{" +
                "id='" + id + '\'' +
                ", createAt='" + createAt + '\'' +
                ", icon='" + icon + '\'' +
                ", firstCategoryId='" + firstCategoryId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
