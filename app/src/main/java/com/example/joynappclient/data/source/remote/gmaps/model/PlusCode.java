package com.example.joynappclient.data.source.remote.gmaps.model;

import com.google.gson.annotations.SerializedName;

public class PlusCode {

    @SerializedName("compound_code")
    private String compoundCode;

    @SerializedName("global_code")
    private String globalCode;

    public String getCompoundCode() {
        return compoundCode;
    }

    public String getGlobalCode() {
        return globalCode;
    }
}