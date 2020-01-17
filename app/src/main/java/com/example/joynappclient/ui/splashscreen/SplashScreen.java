package com.example.joynappclient.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.ui.authentication.login_register.AuthenticationActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        startActivity(new Intent(SplashScreen.this, AuthenticationActivity.class));
        finish();
    }
}
