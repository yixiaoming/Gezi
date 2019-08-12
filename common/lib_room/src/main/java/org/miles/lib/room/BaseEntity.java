package org.miles.lib.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The entity is for test, no need to extends
 */
@Entity
public class BaseEntity {

    @PrimaryKey
    @ColumnInfo()
    public long id;
    public String name;
}
