package org.miles.gezi;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.miles.room.AppDatabase;
import org.miles.room.User;

import java.util.List;
import java.util.Random;

public class MainViewModel extends ViewModel {

    public static final String TAG = "MainViewModel";

    public void insertUser() {
        User user = new User();
        user.name = "yxm" + (new Random().nextInt(1000));
        AppDatabase.get().userDao().insert(user);
    }

    public void showUsers() {
        List<User> userList = AppDatabase.get().getUserDao().selectAll();
        Log.d(TAG, "showUsers: " + userList);
    }

    public void showUser() {
        User user = AppDatabase.get().getUserDao().selectById(1);
        Log.d(TAG, "showUser: " + user);
    }
}
