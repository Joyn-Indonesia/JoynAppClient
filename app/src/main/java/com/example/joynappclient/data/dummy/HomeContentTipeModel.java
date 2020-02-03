package com.example.joynappclient.data.dummy;

import java.util.List;

public class HomeContentTipeModel {

    private String tipe;

    private String higLight;

    private List<HomeContentDataModel> contentData;

    public HomeContentTipeModel(String tipe, String higLight, List<HomeContentDataModel> contentData) {
        this.tipe = tipe;
        this.higLight = higLight;
        this.contentData = contentData;
    }

    public String getHigLight() {
        return higLight;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public List<HomeContentDataModel> getContentData() {
        return contentData;
    }

    public void setContentData(List<HomeContentDataModel> contentData) {
        this.contentData = contentData;
    }
}
