package com.example.joynappclient.ui.booking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;

public class BindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        Button acc = findViewById(R.id.btn_acc);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MoveActivity.MoveAct(BindingActivity.this, PickupActivity.class);
            }
        });
    }
}
