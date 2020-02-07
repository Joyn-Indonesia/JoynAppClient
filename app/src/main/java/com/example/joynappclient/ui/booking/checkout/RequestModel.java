package com.example.joynappclient.ui.booking.checkout;

import com.google.android.gms.maps.model.LatLng;

public class RequestModel {

    private LatLng pickupLatLg;
    private LatLng destinationLatLg;
    private String userName;
    private String pickupAddress;
    private String destinationAddress;

    public RequestModel() {
    }

    public LatLng getPickupLatLg() {
        return pickupLatLg;
    }

    public void setPickupLatLg(LatLng pickupLatLg) {
        this.pickupLatLg = pickupLatLg;
    }

    public LatLng getDestinationLatLg() {
        return destinationLatLg;
    }

    public void setDestinationLatLg(LatLng destinationLatLg) {
        this.destinationLatLg = destinationLatLg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
