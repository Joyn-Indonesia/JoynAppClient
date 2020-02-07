package com.example.joynappclient.utils;

import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;

public class Constant extends AppCompatActivity {

    //KEY
    public static final String FCM_KEY = "AIzaSyCgJuIHUC4mutg5ahFsZW5VjUhCHwH5PQY";

    //permision
    public static final int REQUEST_PERMISSIONS = 1001;
    public static final int REQUEST_ENABLE_GPS = 1002;
    public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 1003;

    public static final int ERROR_DIALOG_REQUEST = 9001;
    public static final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    // if you use distance in KM then
    public static final String UNIT_OF_DISTANCE = "Km"; //if you use km or miles
    public static final Float RANGE_VALUE = 1000f; //if using km (1000f) or Miles using 1609f
    // Currency settings
    public static final String MONEY = "Rp.";

    //preffConstant
    public static final String REG_ID = "reg_id";

}
