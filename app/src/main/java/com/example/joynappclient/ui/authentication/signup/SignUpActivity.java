package com.example.joynappclient.ui.authentication.signup;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.signin.SignInActivity;
import com.example.joynappclient.utils.MoveActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    //vars
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick(R.id.btn_regiter)
    public void register() {
        MoveActivity.MoveAct(context, SignInActivity.class);
    }

    @OnClick(R.id.tv_signIn)
    public void moveToSignin() {
        MoveActivity.MoveAct(context, SignInActivity.class);
    }
}
