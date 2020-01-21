package com.example.joynappclient.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.ui.authentication.signup.SignUpActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, SignUpActivity.class));
            finish();
        }, 3000);


    }
}
