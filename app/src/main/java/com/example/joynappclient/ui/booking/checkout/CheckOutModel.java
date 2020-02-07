package com.example.joynappclient.ui.booking.checkout;

import com.example.joynappclient.data.source.local.entity.UserLogin;
import com.example.joynappclient.data.source.remote.model.DriverModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class CheckOutModel {

    private String pickupAdress;
    private String destintaionAddress;
    private String distance;
    private String timeDistance;
    private String cost;
    private String note;
    private LatLng pickupLatLg;
    private LatLng destinationLatLg;
    private UserLogin userBooking;
    private List<DriverModel> drivers;

    public CheckOutModel() {
    }

    public String getPickupAdress() {
        return pickupAdress;
    }

    public void setPickupAdress(String pickupAdress) {
        this.pickupAdress = pickupAdress;
    }

    public String getDestintaionAddress() {
        return destintaionAddress;
    }

    public void setDestintaionAddress(String destintaionAddress) {
        this.destintaionAddress = destintaionAddress;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(String timeDistance) {
        this.timeDistance = timeDistance;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public UserLogin getUserBooking() {
        return userBooking;
    }

    public void setUserBooking(UserLogin userBooking) {
        this.userBooking = userBooking;
    }

    public List<DriverModel> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverModel> drivers) {
        this.drivers = drivers;
    }
}
