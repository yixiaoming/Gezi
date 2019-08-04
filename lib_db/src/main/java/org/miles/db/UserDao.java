package org.miles.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;

import org.miles.db.base.BaseDao;

import java.util.List;

@Dao
public abstract class UserDao extends BaseDao<User> {

    @Transaction
    public long insert(User user, List<Address> addresses) {
        for (Address address : addresses) {
            address.userId = user.id;
        }
        insertAddresses(addresses);
        return insert(user);
    }

    @Insert
    public abstract long[] insertAddresses(List<Address> addresses);
}
