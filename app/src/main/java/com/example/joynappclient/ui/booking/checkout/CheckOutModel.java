package com.example.joynappclient.ui.booking.checkout;

public class CheckOutModel {

    private String pickupAdress;
    private String destintaionAddress;
    private String distance;
    private String timeDistance;
    private String cost;

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
}
