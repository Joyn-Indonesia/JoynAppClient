package com.example.joynappclient.data.dummy;

public class CartModel {

    int imgContent;

    String title;

    String price;

    public CartModel(int imgContent, String title, String price) {
        this.imgContent = imgContent;
        this.title = title;
        this.price = price;
    }

    public int getImgContent() {
        return imgContent;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
