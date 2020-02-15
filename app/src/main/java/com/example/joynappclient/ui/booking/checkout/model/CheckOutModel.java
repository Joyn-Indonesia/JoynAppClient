package com.example.joynappclient.ui.booking.checkout.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.joynappclient.data.source.local.entity.LocalUserLogin;
import com.example.joynappclient.data.source.remote.model.DriverModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.model.EncodedPolyline;

import java.util.List;

public class CheckOutModel implements Parcelable {

    private String pickupAdress;
    private String destintaionAddress;
    private String distance;
    private String timeDistance;
    private String cost;
    private String note;
    private LatLng pickupLatLg;
    private LatLng destinationLatLg;
    private LocalUserLogin userBooking;
    public static final Creator<CheckOutModel> CREATOR = new Creator<CheckOutModel>() {
        @Override
        public CheckOutModel createFromParcel(Parcel in) {
            return new CheckOutModel(in);
        }

        @Override
        public CheckOutModel[] newArray(int size) {
            return new CheckOutModel[size];
        }
    };
    private List<DriverModel> drivers;
    private EncodedPolyline polyline;
    private LatLngBounds latLngBounds;

    public CheckOutModel() {
    }

    private EncodedPolyline encodedPolyline;

    protected CheckOutModel(Parcel in) {
        pickupAdress = in.readString();
        destintaionAddress = in.readString();
        distance = in.readString();
        timeDistance = in.readString();
        cost = in.readString();
        note = in.readString();
        pickupLatLg = in.readParcelable(LatLng.class.getClassLoader());
        destinationLatLg = in.readParcelable(LatLng.class.getClassLoader());
        latLngBounds = in.readParcelable(LatLngBounds.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pickupAdress);
        dest.writeString(destintaionAddress);
        dest.writeString(distance);
        dest.writeString(timeDistance);
        dest.writeString(cost);
        dest.writeString(note);
        dest.writeParcelable(pickupLatLg, flags);
        dest.writeParcelable(destinationLatLg, flags);
        dest.writeParcelable(latLngBounds, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public EncodedPolyline getEncodedPolyline() {
        return encodedPolyline;
    }

    public void setEncodedPolyline(EncodedPolyline encodedPolyline) {
        this.encodedPolyline = encodedPolyline;
    }

    public LatLngBounds getLatLngBounds() {
        return latLngBounds;
    }

    public void setLatLngBounds(LatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
    }

    public EncodedPolyline getPolyline() {
        return polyline;
    }

    public void setPolyline(EncodedPolyline polyline) {
        this.polyline = polyline;
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

    public LocalUserLogin getUserBooking() {
        return userBooking;
    }

    public void setUserBooking(LocalUserLogin userBooking) {
        this.userBooking = userBooking;
    }

    public List<DriverModel> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverModel> drivers) {
        this.drivers = drivers;
    }
}
