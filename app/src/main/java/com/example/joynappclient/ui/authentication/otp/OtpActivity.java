package com.example.joynappclient.ui.authentication.otp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.UserModel;
import com.example.joynappclient.utils.Constant;
import com.goodiebag.pinview.Pinview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends AppCompatActivity {
    private static final String TAG = "OtpActivity";

    //widget
    @BindView(R.id.tv_numberPhone)
    TextView numberText;
    @BindView(R.id.pinview)
    Pinview pinCode;
    @BindView(R.id.btn_next)
    Button next;

    //vars
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        context = this;


        UserModel phone = getIntent().getParcelableExtra(Constant.User);
        Log.d(TAG, "onCreate: " + phone);

        numberText.setText(phone.getPhoneNumber());

        pinCode.setPinViewEventListener((pinview, fromUser)
                -> {
            Toast.makeText(context, pinview.getValue(), Toast.LENGTH_SHORT).show();
            next.setEnabled(true);

        });
    }

    private void requestOtp() {

    }

    @OnClick(R.id.btn_changeNumber)
    public void changeNumber() {
        finish();
    }

    @OnClick(R.id.btn_next)
    public void next() {
        // MoveActivity.MoveAct(context, WelcomeToAppActivity.class);
        Toast.makeText(context, "nmax", Toast.LENGTH_SHORT).show();
    }
}
