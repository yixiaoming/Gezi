package org.miles.lib.data.gank.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import org.miles.lib.room.conversion.ListStringConversion;

import java.util.List;

@Entity(tableName = "t_gank")
public class GankEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @SerializedName("_id")
    public String gankid;
    @ColumnInfo(name = "create_at")
    public String createdAt;
    public String desc;
    @ColumnInfo(name = "published_at")
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public Boolean used;
    public String who;
    @TypeConverters(ListStringConversion.class)
    public List<String> images;

    @Override
    public String toString() {
        return "GankEntity{" +
                "id='" + id + '\'' +
                ", gankid='" + gankid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", images=" + images +
                '}';
    }
}
