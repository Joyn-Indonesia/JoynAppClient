package com.example.joynappclient.ui.booking;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.model.json.book.RequestRideCarRequestJson;
import com.example.joynappclient.data.source.remote.model.DriverModel;
import com.example.joynappclient.ui.booking.utils.HandleResponse;
import com.example.joynappclient.ui.booking.utils.StatusResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class BookingViewModel extends ViewModel {
    private static final String TAG = "BookingViewModel";


    private String addressLocation;
    private Context context;
    private String note;
    private MutableLiveData<HandleResponse<Place>> responsePickUp = new MutableLiveData<>();
    private MutableLiveData<HandleResponse<Place>> responseDestination = new MutableLiveData<>();
    private MutableLiveData<RequestRideCarRequestJson> requestRideCar = new MutableLiveData<>();
    private MutableLiveData<List<DriverModel>> driver = new MutableLiveData<>();


    public BookingViewModel(@NonNull Context context) {
        this.context = context;

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

    public LiveData<RequestRideCarRequestJson> getRequestRideCar() {
        return requestRideCar;
    }

    public void setRequestRideCar(RequestRideCarRequestJson requestRideCar) {
        requestRideCar.setCatatan(this.note);
        this.requestRideCar.postValue(requestRideCar);
    }

    public void setNote(String note) {
        this.note = note;
        RequestRideCarRequestJson param = new RequestRideCarRequestJson();
        param.setCatatan(note);
        requestRideCar.postValue(param);
    }


    public void setDriverAvaible(List<DriverModel> driver) {
        this.driver.postValue(driver);
    }

    public LiveData<List<DriverModel>> getDriver() {
        return driver;
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
