package org.miles.lib.room;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miles.lib.room.test.Address;
import org.miles.lib.room.test.AddressDao;
import org.miles.lib.room.test.AppDatabase;
import org.miles.lib.room.test.User;
import org.miles.lib.room.test.UserAddresses;
import org.miles.lib.room.test.UserAddressesDao;
import org.miles.lib.room.test.UserDao;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UserAddressesDaoTest {

    private UserAddressesDao mUserAddressesDao;
    private UserDao mUserDao;
    private AddressDao mAddressDao;


    @Before
    public void init() {
        Context context = ApplicationProvider.getApplicationContext();
        mUserAddressesDao = AppDatabase.get(context).userAddressesDao();
        mUserDao = AppDatabase.get(context).userDao();
        mAddressDao = AppDatabase.get(context).addressDao();
        initDb();
    }

    private void initDb() {
        for (int i = 1; i <= 9; i++) {
            User user = new User();
            user.id = i;
            user.name = "name" + i;

            Address address1 = new Address();
            address1.id = i * 10;
            address1.userId = i;
            address1.addr = "addr" + i;
            address1.post = "post" + i;

            Address address2 = new Address();
            address2.id = i * 100;
            address2.userId = i;
            address2.addr = "addr" + i * 100;
            address2.post = "post" + i * 100;

            List<Address> list = new ArrayList<>();
            list.add(address1);
            list.add(address2);
            mUserDao.insert(user, list);
        }
    }

    @After
    public void clear() {
        AppDatabase.get().clear();
    }

    @Test
    public void selectAll() {
        List<UserAddresses> list = mUserAddressesDao.selectAll();
        for (UserAddresses item : list) {
            System.out.println(item);
        }
    }
}
