package org.miles.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;

import org.miles.room.base.BaseDao;

import java.util.List;

@Dao
public abstract class UserDao extends BaseDao<User> {

    @Transaction
    public long insert(User user, List<Address> addresses) {
        for (Address address : addresses) {
            address.userId = user.id;
        }
        long result = insert(user);
        insertAddresses(addresses);
        return result;
    }

    @Insert
    public abstract long[] insertAddresses(List<Address> addresses);
}
