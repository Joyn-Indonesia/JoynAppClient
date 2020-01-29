package com.example.joynappclient.ui.booking;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.adapter.ExampleButtomSheetDialog;
import com.example.joynappclient.ui.booking.checkout.CheckoutActivity;
import com.example.joynappclient.utils.MoveActivity;

public class BookingActivity extends AppCompatActivity implements ExampleButtomSheetDialog.BottomSheetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ConstraintLayout button1 = findViewById(R.id.cl_pickup);
        Button btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(v -> MoveActivity.MoveAct(BookingActivity.this, CheckoutActivity.class));

        button1.setOnClickListener(v -> {
            ExampleButtomSheetDialog ex = new ExampleButtomSheetDialog();
            ex.show(getSupportFragmentManager(), "");
        });

    }


    @Override
    public void onButtonClicked(String text) {
        TextView tv = findViewById(R.id.tv_locationPickup);
        tv.setText(text);
    }
}
