package com.example.joynappclient.ui.booking.checkout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.paymen.PaymenCarActivity;
import com.example.joynappclient.ui.booking.paymen.PaymenRideActivity;
import com.example.joynappclient.utils.MoveActivity;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Button select = findViewById(R.id.btn_select);
        ImageView car = findViewById(R.id.iv_jcar);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveActivity.MoveAct(CheckoutActivity.this, PaymenCarActivity.class);
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveActivity.MoveAct(CheckoutActivity.this, PaymenRideActivity.class);
            }
        });
    }
}
