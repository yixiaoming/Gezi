package org.miles.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserAddresses {

    @Embedded
    public User user;

    @Relation(entity = Address.class, parentColumn = "id", entityColumn = "userId")
    public List<Address> addresses;
}
