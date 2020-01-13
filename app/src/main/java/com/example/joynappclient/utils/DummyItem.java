package com.example.joynappclient.utils;

import com.example.joynappclient.R;
import com.example.joynappclient.data.JfoodModelDummyl;

import java.util.ArrayList;
import java.util.List;

public class DummyItem {

    public static List<JfoodModelDummyl> getFoodSnack() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.martabak, "Martabak"));
        snack.add(new JfoodModelDummyl(R.drawable.burjodurian, "Bubur Ijo Durian"));
        snack.add(new JfoodModelDummyl(R.drawable.singkong_keju, "Singkong Coklat Keju"));
        return snack;
    }

    public static List<JfoodModelDummyl> getNearbyReto() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.kopikenangan, "Kopi Kenangan"));
        snack.add(new JfoodModelDummyl(R.drawable.satebabi, "Sate Babi Manis"));
        snack.add(new JfoodModelDummyl(R.drawable.ayambetutu, "Ayam Betutu Klampis"));
        return snack;
    }

    public static List<JfoodModelDummyl> getBestPick() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JfoodModelDummyl(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JfoodModelDummyl(R.drawable.platinum, "Platinum Grill"));
        return snack;
    }

    public static List<JfoodModelDummyl> getDhisesLike() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.baksokober, "Bakso Kober"));
        snack.add(new JfoodModelDummyl(R.drawable.rujaksoto, "Rujak Soto"));
        snack.add(new JfoodModelDummyl(R.drawable.nasibekepor, "Nasi Bakepor"));
        return snack;
    }

    public static List<JfoodModelDummyl> getBreakfast() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.segojagung, "McDonald manyar"));
        snack.add(new JfoodModelDummyl(R.drawable.segojagung, "MrD Coffe Sukolilo"));
        snack.add(new JfoodModelDummyl(R.drawable.burjodurian, "Platinum Grill"));
        return snack;
    }

    public static List<JfoodModelDummyl> getHighligtedDishes() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JfoodModelDummyl(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JfoodModelDummyl(R.drawable.platinum, "Platinum Grill"));
        return snack;
    }

    public static List<JfoodModelDummyl> getRecomendResto() {
        List<JfoodModelDummyl> snack = new ArrayList<>();
        snack.add(new JfoodModelDummyl(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JfoodModelDummyl(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JfoodModelDummyl(R.drawable.platinum, "Platinum Grill"));
        return snack;
    }

}
