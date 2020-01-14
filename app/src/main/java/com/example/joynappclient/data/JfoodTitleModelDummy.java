package com.example.joynappclient.data;

import java.util.List;

public class JfoodTitleModelDummy {

    private String title;

    private List<JFoodContentModelDummy> content;

    public JfoodTitleModelDummy(String title, List<JFoodContentModelDummy> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public List<JFoodContentModelDummy> getContent() {
        return content;
    }
}


