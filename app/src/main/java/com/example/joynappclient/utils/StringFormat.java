package com.example.joynappclient.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class StringFormat {

    public static String distanceFormat(Double distance){
        return String.format(Locale.US, "Distance %.2f " + Constant.UNIT_OF_DISTANCE, distance);
    }

    public static String costFormat(Double cost){
        String format =  NumberFormat.getNumberInstance(Locale.US).format(cost);
        return String.format(Locale.US, Constant.MONEY + " %s.000,-", format);
    }
}
