package org.miles.kaiyan.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KaiyanCategory {

    @NonNull
    @PrimaryKey
    public long id;
    public String name;
    public String description;
    public String bgPicture;
    public String bgColor;
    public String headerImage;
    public String defaultAuthorId;

    @Override
    public String toString() {
        return "KaiyanCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", bgPicture='" + bgPicture + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", headerImage='" + headerImage + '\'' +
                ", defaultAuthorId='" + defaultAuthorId + '\'' +
                '}';
    }
}
