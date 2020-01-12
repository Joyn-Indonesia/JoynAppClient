package com.example.joynappclient.utils;

import android.content.Context;
import android.content.Intent;

public class MoveActivity {

    public static void MoveAct(Context context, Class<?> destinaation) {
        context.startActivity(new Intent(context, destinaation));
    }

}
