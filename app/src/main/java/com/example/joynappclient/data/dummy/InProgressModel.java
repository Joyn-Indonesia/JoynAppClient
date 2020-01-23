package com.example.joynappclient.data.dummy;

public class InProgressModel {

    String dateOrder;

    String addressOrder;

    String timeOrder;

    public InProgressModel(String dateOrder, String addressOrder, String timeOrder) {
        this.dateOrder = dateOrder;
        this.addressOrder = addressOrder;
        this.timeOrder = timeOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public String getAddressOrder() {
        return addressOrder;
    }

    public String getTimeOrder() {
        return timeOrder;
    }
}
