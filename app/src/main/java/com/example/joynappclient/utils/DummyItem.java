package com.example.joynappclient.utils;

import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.CartModel;
import com.example.joynappclient.data.dummy.FoodModel;
import com.example.joynappclient.data.dummy.InProgressModel;
import com.example.joynappclient.data.dummy.JFoodContentModelDummy;
import com.example.joynappclient.data.dummy.JfoodTitleModelDummy;

import java.util.ArrayList;
import java.util.List;

public class DummyItem {

    public static List<JFoodContentModelDummy> getFoodSnack() {
        List<JFoodContentModelDummy> snack = new ArrayList<>();

        snack.add(new JFoodContentModelDummy(R.drawable.martabak, "Martabak"));
        snack.add(new JFoodContentModelDummy(R.drawable.burjodurian, "Bubur Ijo Durian"));
        snack.add(new JFoodContentModelDummy(R.drawable.singkong_keju, "Singkong Coklat Keju"));
        snack.add(new JFoodContentModelDummy(R.drawable.martabak, "Martabak"));
        snack.add(new JFoodContentModelDummy(R.drawable.burjodurian, "Bubur Ijo Durian"));
        snack.add(new JFoodContentModelDummy(R.drawable.singkong_keju, "Singkong Coklat Keju"));
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
        snack.add(new JFoodContentModelDummy(R.drawable.kopikenangan, "Kopi Kenangan"));
        snack.add(new JFoodContentModelDummy(R.drawable.satebabi, "Sate Babi Manis"));
        snack.add(new JFoodContentModelDummy(R.drawable.ayambetutu, "Ayam Betutu Klampis"));
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
        snack.add(new JFoodContentModelDummy(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.platinum, "Platinum Grill"));
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
        snack.add(new JFoodContentModelDummy(R.drawable.baksokober, "Bakso Kober"));
        snack.add(new JFoodContentModelDummy(R.drawable.rujaksoto, "Rujak Soto"));
        snack.add(new JFoodContentModelDummy(R.drawable.nasibekepor, "Nasi Bakepor"));
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
        snack.add(new JFoodContentModelDummy(R.drawable.segojagung, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.segojagung, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.burjodurian, "Platinum Grill"));
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
        snack.add(new JFoodContentModelDummy(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.platinum, "Platinum Grill"));
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
        snack.add(new JFoodContentModelDummy(R.drawable.mcd_manyar, "McDonald manyar"));
        snack.add(new JFoodContentModelDummy(R.drawable.mrd_coffee, "MrD Coffe Sukolilo"));
        snack.add(new JFoodContentModelDummy(R.drawable.platinum, "Platinum Grill"));
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

    public static List<CartModel> getCart() {
        List<CartModel> cart = new ArrayList<>();

        cart.add(new CartModel(R.drawable.img_rawon2, "Rawon", "Rp. 25000"));
        cart.add(new CartModel(R.drawable.img_ekrim_zangrandi, "Es Krim", "Rp. 15000"));

        return cart;
    }

    public static List<FoodModel> getFood() {
        List<FoodModel> food = new ArrayList<>();
        food.add(new FoodModel(R.drawable.img_ayamrujak, "Rujak Ayam", "Sayur ayam dirujak", "Rp.75.000"));
        food.add(new FoodModel(R.drawable.img_sateklopo2, "Sate Ayam", "Steak daging ayam + sayur + lontong", "Rp.50.000"));
        food.add(new FoodModel(R.drawable.img_empalpengampon, "Paket steak daging kambing", "Steak daging kambing + sayur + longtng", "Rp.100.000"));
        food.add(new FoodModel(R.drawable.img_ekrim_zangrandi, "Capucino Cincau Ice", "Capucino bubuk, almond milk ", "Rp.15.000"));
        return food;
    }

    public static List<InProgressModel> getInProgress() {
        List<InProgressModel> progress = new ArrayList<>();
        progress.add(new InProgressModel("10 jan 2020", "Jl. majapahit no 3", "17:45"));
        progress.add(new InProgressModel("10 jan 2020", "Jl. majapahit no 3", "17:45"));

        return progress;
    }

    public static List<InProgressModel> getHistory() {
        List<InProgressModel> progress = new ArrayList<>();
        progress.add(new InProgressModel("10 jan 2020", "Jl. majapahit no 1", "17:45"));
        progress.add(new InProgressModel("10 jan 2077", "Jl. majapahit no 2", "17:45"));
        progress.add(new InProgressModel("10 jan 2088", "Jl. majapahit no 3", "19:45"));
        progress.add(new InProgressModel("10 jan 2099", "Jl. majapahit no 4", "19:45"));

        return progress;
    }

}
