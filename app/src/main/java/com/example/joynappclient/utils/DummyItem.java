package com.example.joynappclient.utils;

import com.example.joynappclient.R;
import com.example.joynappclient.data.JFoodContentModelDummy;
import com.example.joynappclient.data.JfoodTitleModelDummy;

import java.util.ArrayList;
import java.util.List;

public class DummyItem {

    public static List<JFoodContentModelDummy> getFoodSnack() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();

        snack.add(new JFoodContentModelDummy(R.drawable.martabak, "Martabak"));
        snack.add(new JFoodContentModelDummy(R.drawable.burjodurian, "Bubur Ijo Durian"));
        snack.add(new JFoodContentModelDummy(R.drawable.singkong_keju, "Singkong Coklat Keju"));
        return snack;
    }

    public static List<JFoodContentModelDummy> getNearbyReto() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();
        snack.add(new JFoodContentModelDummy(R.drawable.kopikenangan, "Kopi Kenangan"));
        snack.add(new JFoodContentModelDummy(R.drawable.satebabi, "Sate Babi Manis"));
        snack.add(new JFoodContentModelDummy(R.drawable.ayambetutu, "Ayam Betutu Klampis"));
        return snack;
    }

    public static List<JFoodContentModelDummy> getBestPick() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();
        snack.add(new JFoodContentModelDummy(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.platinum, "Platinum Grill"));
        return snack;
    }

    public static List<JFoodContentModelDummy> getDhisesLike() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();
        snack.add(new JFoodContentModelDummy(R.drawable.baksokober, "Bakso Kober"));
        snack.add(new JFoodContentModelDummy(R.drawable.rujaksoto, "Rujak Soto"));
        snack.add(new JFoodContentModelDummy(R.drawable.nasibekepor, "Nasi Bakepor"));
        return snack;
    }

    public static List<JFoodContentModelDummy> getBreakfast() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();
        snack.add(new JFoodContentModelDummy(R.drawable.segojagung, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.segojagung, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.burjodurian, "Platinum Grill"));
        return snack;
    }

    public static List<JFoodContentModelDummy> getHighligtedDishes() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();
        snack.add(new JFoodContentModelDummy(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.platinum, "Platinum Grill"));
        return snack;
    }

    public static List<JFoodContentModelDummy> getRecomendResto() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();
        snack.add(new JFoodContentModelDummy(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.platinum, "Platinum Grill"));
        return snack;
    }

    public static List<JfoodTitleModelDummy> getAllData() {
        List<JfoodTitleModelDummy> data = new ArrayList<>();
        data.add(new JfoodTitleModelDummy("Afternoon snack for anyone", getFoodSnack()));
        data.add(new JfoodTitleModelDummy("Recomended Resto", getRecomendResto()));
        data.add(new JfoodTitleModelDummy("Nearby Restaurant", getNearbyReto()));
        data.add(new JfoodTitleModelDummy("Best Pick In Surabaya", getBestPick()));
        data.add(new JfoodTitleModelDummy("Dishes that are well liked", getDhisesLike()));
        data.add(new JfoodTitleModelDummy("Intereting Breakfast", getBreakfast()));
        data.add(new JfoodTitleModelDummy("Hightlighted Dishes", getHighligtedDishes()));

        return data;
    }

}
