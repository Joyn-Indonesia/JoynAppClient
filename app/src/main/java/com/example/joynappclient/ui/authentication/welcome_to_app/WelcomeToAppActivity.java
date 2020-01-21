package com.example.joynappclient.ui.authentication.welcome_to_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.main_menu.MainMenuActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeToAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to_app);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_tryOur)
    public void MainMenu() {
        startActivity(new Intent(WelcomeToAppActivity.this, MainMenuActivity.class));
        finishAffinity();
    }
}
