package com.example.joynappclient.data.source.remote.gmaps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsItem {

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("types")
    private List<String> types;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("address_components")
    private List<AddressComponentsItem> addressComponents;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("plus_code")
    private PlusCode plusCode;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public List<String> getTypes() {
        return types;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public List<AddressComponentsItem> getAddressComponents() {
        return addressComponents;
    }

    public String getPlaceId() {
        return placeId;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }
}