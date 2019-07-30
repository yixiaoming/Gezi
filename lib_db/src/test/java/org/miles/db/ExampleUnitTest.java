package org.miles.db;

import android.annotation.TargetApi;
import android.content.Context;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {

    private AppDatabase mDb;
    private UserDao mUserDao;

    @Before
    public void init() {
        Context context = InstrumentationRegistry.getTargetContext();
        AppDatabase mDb = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        mUserDao = mDb.userDao();

        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.id = i;
            user.name = "name" + i;
            mUserDao.insert(user);
        }
    }

    @After
    public void close() {
        mDb.close();
    }

    @TargetApi(28)
    @Test
    public void test() {
        System.out.println("1");
        Assert.assertEquals(1, 1);
        System.out.println("2");
    }
}