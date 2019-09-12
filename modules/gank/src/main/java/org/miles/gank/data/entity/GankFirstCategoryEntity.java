package org.miles.gank.data.entity;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class GankFirstCategoryEntity {

    @ColumnInfo(name = BaseColumns._ID)
    @SerializedName("_id")
    public String id;

    @SerializedName("en_name")
    public String enName;

    public String name;

    public Integer rank;

    @Override
    public String toString() {
        return "GankFirstCategoryEntity{" +
                "id='" + id + '\'' +
                ", enName='" + enName + '\'' +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
