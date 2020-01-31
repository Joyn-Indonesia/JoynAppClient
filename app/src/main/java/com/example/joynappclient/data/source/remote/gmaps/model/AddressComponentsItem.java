package com.example.joynappclient.data.source.remote.gmaps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressComponentsItem {

    @SerializedName("types")
    private List<String> types;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("long_name")
    private String longName;

    public List<String> getTypes() {
        return types;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }
}