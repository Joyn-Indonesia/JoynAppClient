package com.example.joynappclient.data.dummy;

public class HomeContentDataModel {

    private int imageContent;

    private String title;

    private String content;

    public HomeContentDataModel(int imageContetn, String title, String content) {
        this.imageContent = imageContetn;
        this.title = title;
        this.content = content;
    }

    public int getImageContetn() {
        return imageContent;
    }

    public void setImageContetn(int imageContetn) {
        this.imageContent = imageContetn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
