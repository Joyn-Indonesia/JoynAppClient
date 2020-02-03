package com.example.joynappclient.data.source.remote.model;

import com.google.firebase.firestore.GeoPoint;

public class DriverModel {

    private GeoPoint Location;

    public DriverModel(GeoPoint geoPoint) {
        this.Location = geoPoint;
    }

    public GeoPoint getGeoPoint() {
        return Location;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.Location = geoPoint;
    }
}
