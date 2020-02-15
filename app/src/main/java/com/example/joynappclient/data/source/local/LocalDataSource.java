package com.example.joynappclient.data.source.local;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.joynappclient.data.source.local.entity.LocalUserLogin;
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

    public void insertUserLogin(LocalUserLogin localUserLogin) {
        Log.d(TAG, "insertUserLogin:saving");
        joynDao.insertLoginUser(localUserLogin);
    }

    public LiveData<LocalUserLogin> getUserLogin() {
        return joynDao.getUserLogin();
    }

    public void updateUserLogin(LocalUserLogin localUserLogin) {
        joynDao.updateUserLogin(localUserLogin);
    }

    public void deleteUserLogin() {
        Log.d(TAG, "deleteUserLogin: deleted");
        joynDao.deleteUserLogin();
    }
}
