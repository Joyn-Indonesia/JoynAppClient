package com.example.joynappclient.ui.booking.paymen;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BindingActivity;
import com.example.joynappclient.utils.MoveActivity;

public class PaymenCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymen_car);
        Button next = findViewById(R.id.btn_next);
        next.setOnClickListener(v -> MoveActivity.MoveAct(PaymenCarActivity.this, BindingActivity.class));
    }
}
