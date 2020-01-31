package com.example.joynappclient.data.source.remote.gmaps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGmaps {


    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("status")
    private String status;

    public List<ResultsItem> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}