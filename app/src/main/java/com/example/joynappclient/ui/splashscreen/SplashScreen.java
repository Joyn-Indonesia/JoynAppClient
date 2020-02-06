package com.example.joynappclient.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.signup.SignUpActivity;
import com.example.joynappclient.ui.main_menu.MainMenuActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";


    //vars
    private boolean stateSignedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(this::setupFirebaseAuth, 3000);
    }

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: auth started");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(TAG, "onAuthStateChanged: signed in" + user.getUid());
            moveToHome();
            finish();
        } else {
            Log.d(TAG, "onAuthStateChanged:signed_out");
            moveToRegister();
            finish();
        }
    }

    private void moveToHome() {
        Intent intent = new Intent(SplashScreen.this, MainMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void moveToRegister() {
        Intent intent = new Intent(SplashScreen.this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
