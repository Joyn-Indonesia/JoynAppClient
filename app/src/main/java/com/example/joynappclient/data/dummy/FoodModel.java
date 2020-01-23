package com.example.joynappclient.data.dummy;

public class FoodModel {

    String titleContent;
    String content;
    String price;
    private int contentImage;

    public FoodModel(int contentImage, String titleContent, String content, String price) {
        this.contentImage = contentImage;
        this.titleContent = titleContent;
        this.content = content;
        this.price = price;
    }

    public int getContentImage() {
        return contentImage;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public String getContent() {
        return content;
    }

    public String getPrice() {
        return price;
    }
}
