package org.miles.gank.data.entity;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class GankSecondCategoryEntity {

    @PrimaryKey
    @ColumnInfo(name = BaseColumns._ID)
    @SerializedName("_id")
    public String id;

    @SerializedName("create_at")
    public String createAt;

    public String icon;

    @SerializedName("id")
    public String categoryId;

    public String title;

    @Override
    public String toString() {
        return "GankSecondCategoryEntity{" +
                "id='" + id + '\'' +
                ", createAt='" + createAt + '\'' +
                ", icon='" +  icon + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
