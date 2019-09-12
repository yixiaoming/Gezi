package org.miles.kaiyan.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by yxm on 2018.7.29.
 */

@Entity
public class KaiyanVideoItem {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "auto_id")
  public long autoId;

  public String type;
  @ColumnInfo(name = "tab_id")
  public int tabId;
  @Embedded
  public Data data;

  @Override
  public String toString() {
    return "KaiyanVideoItem{" +
        "type='" + type + '\'' +
        ", data=" + data +
        '}';
  }

  public static class Data {

    @ColumnInfo(name = "data_id")
    public long id;

    public String title;
    @ColumnInfo(name = "data_desc")
    public String description;
    @Embedded
    public Author author;
    public String playUrl;
    public int duration;
    public String date;
    @Embedded
    public Cover cover;
    @Embedded
    public WebUrl webUrl;


    @Override
    public String toString() {
      return "Data{" +
          "title='" + title + '\'' +
          ", description='" + description + '\'' +
          ", author=" + author +
          ", playUrl='" + playUrl + '\'' +
          ", duration=" + duration +
          ", date='" + date + '\'' +
          '}';
    }

    public static class Author {

      public String icon;
      public String name;
      @ColumnInfo(name = "author_desc")
      public String description;

      @Override
      public String toString() {
        return "Author{" +
            "icon='" + icon + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
      }
    }

    public static class Cover {

      public String detail;
    }

    public static class WebUrl {

      public String raw;
    }
  }
}
