package com.example.joynappclient.ui.authentication.otp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.welcome_to_app.WelcomeToAppActivity;
import com.example.joynappclient.utils.MoveActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends AppCompatActivity {
    private static final String TAG = "OtpActivity";

    //widget

    //vars
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick(R.id.btn_changeNumber)
    public void changeNumber() {
        finish();
    }

    @OnClick(R.id.btn_next)
    public void next() {
        MoveActivity.MoveAct(context, WelcomeToAppActivity.class);
    }
}
