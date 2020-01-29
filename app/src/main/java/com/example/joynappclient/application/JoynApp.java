package com.example.joynappclient.application;

import android.app.Application;

import com.example.joynappclient.data.source.remote.model.UserModel;

public class JoynApp extends Application {


    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
