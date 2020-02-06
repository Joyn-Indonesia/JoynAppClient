package com.example.joynappclient.data.source.local;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.joynappclient.data.source.local.entity.UserLogin;
import com.example.joynappclient.data.source.local.room.JoynDao;

public class LocalDataSource {
    private static final String TAG = "LocalDataSource";

    private static LocalDataSource INSTANCE;
    private final JoynDao joynDao;

    public LocalDataSource(JoynDao joynDao) {
        this.joynDao = joynDao;
    }

    public static LocalDataSource getInstance(JoynDao joynDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(joynDao);
        }
        return INSTANCE;
    }

    public void insertUserLogin(UserLogin userLogin) {
        Log.d(TAG, "insertUserLogin:saving");
        joynDao.insertLoginUser(userLogin);
    }

    public LiveData<UserLogin> getUserLogin() {
        return joynDao.getUserLogin();
    }

    public void updateUserLogin(UserLogin userLogin) {
        joynDao.updateUserLogin(userLogin);
    }

    public void deleteUserLogin() {
        Log.d(TAG, "deleteUserLogin: deleted");
        joynDao.deleteUserLogin();
    }
}
