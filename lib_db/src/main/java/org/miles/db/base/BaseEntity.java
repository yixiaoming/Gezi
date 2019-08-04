package org.miles.db.base;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BaseEntity {

    @PrimaryKey
    public long id;
    public String name;
}
