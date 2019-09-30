package org.miles.gank.data.entity;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class GankCategoryItemEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = BaseColumns._ID)
    @SerializedName("_id")
    public String id;

    public String categoryId;

    public String content;

    public String cover;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("published_at")
    public String publishedAt;

    public String raw;

    public String title;

    public String uid;

    public String url;

    @Embedded
    public Site site;

    public static class Site {
        @SerializedName("cat_cn")
        public String catCn;

        @SerializedName("cat_en")
        public String catEn;

        public String desc;

        @SerializedName("feed_id")
        public String feedId;

        public String id;

        public String name;

        public String subscribers;

        public String type;

        public String siteUrl;

        @Override
        public String toString() {
            return "Site{" +
                    "catCn='" + catCn + '\'' +
                    ", catEn='" + catEn + '\'' +
                    ", desc='" + desc + '\'' +
                    ", feedId='" + feedId + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", subscribers='" + subscribers + '\'' +
                    ", type='" + type + '\'' +
                    ", siteUrl='" + siteUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GankCategoryItemEntity{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", raw='" + raw + '\'' +
                ", title='" + title + '\'' +
                ", uid='" + uid + '\'' +
                ", url='" + url + '\'' +
                ", site=" + site +
                '}';
    }
}
