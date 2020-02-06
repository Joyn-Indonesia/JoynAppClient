package com.example.joynappclient.application;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.example.joynappclient.data.source.local.entity.UserLogin;
import com.example.joynappclient.utils.Constant;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pixplicity.easyprefs.library.Prefs;

public class JoynApp extends Application {


    private UserLogin userLogin;
    private String token;

    public static JoynApp getInstance(Context context) {
        return (JoynApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initEasyPreff();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(result ->
                token = result.getToken());
        Prefs.putString(Constant.REG_ID, token);
    }


    private void initEasyPreff() {
        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    public String getToken() {
        return token;
    }

    public UserLogin getLoginUser() {
        return userLogin;
    }

    public void setLoginUser(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
