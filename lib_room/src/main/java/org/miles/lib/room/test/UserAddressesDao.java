package org.miles.lib.room.test;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface UserAddressesDao {

    @Transaction
    @Query("SELECT * FROM user")
    List<UserAddresses> selectAll();
}