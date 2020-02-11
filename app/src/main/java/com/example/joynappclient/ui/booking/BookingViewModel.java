package com.example.joynappclient.ui.booking;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.ui.booking.checkout.model.CheckOutModel;
import com.example.joynappclient.ui.booking.utils.HandleResponse;
import com.example.joynappclient.ui.booking.utils.StatusResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class BookingViewModel extends ViewModel {
    private static final String TAG = "BookingViewModel";

    private MutableLiveData<CheckOutModel> checkOut = new MutableLiveData<>();
    private JoynRepository repository;
    private String addressLocation;
    private Context context;
    private CheckOutModel checkOutModel;

    private MutableLiveData<HandleResponse<Place>> responsePickUp = new MutableLiveData<>();
    private MutableLiveData<HandleResponse<Place>> responseDestination = new MutableLiveData<>();


    public BookingViewModel(@NonNull Context context, JoynRepository repository) {

        this.context = context;
        this.repository = repository;
    }


    public void setResponsePlacesPickUp(StatusResponse response, Place place) {
        if (response.equals(StatusResponse.PICKS)) {
            this.responsePickUp.postValue(HandleResponse.picks(null));
        } else {
            this.responsePickUp.postValue(HandleResponse.search(place));
        }
    }

    public void setResponsePlacesDestintaion(StatusResponse response, Place place) {
        if (response.equals(StatusResponse.PICKS)) {
            this.responseDestination.postValue(HandleResponse.picks(null));
        } else {
            this.responseDestination.postValue(HandleResponse.search(place));
        }
    }

    public LiveData<HandleResponse<Place>> getResponsePickUp() {
        return responsePickUp;
    }

    public LiveData<HandleResponse<Place>> getResponseDestination() {
        return responseDestination;
    }

    public void setPickup(LatLng latLng) {
        getAdress(latLng);
    }


    public LiveData<CheckOutModel> getCheckOut() {
        return checkOut;
    }

    public void setNote(String note) {
        checkOutModel = new CheckOutModel();
        checkOutModel.setNote(note);
        checkOut.postValue(checkOutModel);
    }

    public void setCheckOutModel(CheckOutModel checkOut) {

        if (checkOutModel == null) {
            checkOutModel = new CheckOutModel();
        }
        checkOutModel.setPickupAdress(checkOut.getPickupAdress());
        checkOutModel.setDestintaionAddress(checkOut.getDestintaionAddress());
        checkOutModel.setDistance(checkOut.getDistance());
        checkOutModel.setTimeDistance(checkOut.getTimeDistance());
        checkOutModel.setCost(checkOut.getCost());
        checkOutModel.setPickupLatLg(checkOut.getPickupLatLg());
        checkOutModel.setDestinationLatLg(checkOut.getDestinationLatLg());
        checkOutModel.setPolyline(checkOut.getPolyline());
        checkOutModel.setDrivers(checkOut.getDrivers());
        checkOutModel.setLatLngBounds(checkOut.getLatLngBounds());
        checkOutModel.setEncodedPolyline(checkOut.getEncodedPolyline());
        this.checkOut.postValue(checkOutModel);
    }


    public String getAdress(LatLng latLng) {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            final List<Address> adresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (!adresses.isEmpty()) {
                Log.d(TAG, "onNext: " + adresses.size());
                addressLocation = adresses.get(0).getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressLocation;
    }

}
