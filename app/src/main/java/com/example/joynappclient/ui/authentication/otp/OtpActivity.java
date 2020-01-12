package com.example.joynappclient.ui.authentication.otp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.main_menu.MainMenuActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends AppCompatActivity {
    private static final String TAG = "OtpActivity";

    //widget
    @BindView(R.id.btn_next)
    MaterialButton next;

    //var
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        context = this;
        ButterKnife.bind(this);

        next.setOnClickListener(v -> MoveActivity.MoveAct(context, MainMenuActivity.class));
    }
}
