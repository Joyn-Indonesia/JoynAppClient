package com.example.joynappclient.ui.authentication.signin;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.otp.OtpActivity;
import com.example.joynappclient.utils.MoveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    //    wigets
    @BindView(R.id.phone_number)
    AutoCompleteTextView editTextFilledExposedDropdown;
    //    vars
    private String[] COUNTRIES = new String[]{"+62", "+63", "+64"};
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        context = this;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, COUNTRIES
        );
        editTextFilledExposedDropdown.setText(COUNTRIES[0]);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }

    @OnClick(R.id.tv_signUp)
    public void signUp() {
        finish();
    }

    @OnClick({R.id.btn_whatsapp, R.id.btn_sms, R.id.btn_call})
    public void moveToOtp() {
        MoveActivity.MoveAct(context, OtpActivity.class);
    }

}
