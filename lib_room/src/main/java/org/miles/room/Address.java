package org.miles.room;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId"))
public class Address {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String post;
    public String addr;

    public long userId;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", addr='" + addr + '\'' +
                ", userId=" + userId +
                '}';
    }
}
