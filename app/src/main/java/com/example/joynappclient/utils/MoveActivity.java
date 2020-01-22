package com.example.joynappclient.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MoveActivity {

    public static void MoveAct(Context context, Class<?> destinaation) {
        context.startActivity(new Intent(context, destinaation));
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
