package com.example.joynappclient.ui.booking;

import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.ui.booking.checkout.CheckOutModel;
import com.example.joynappclient.ui.booking.utils.HandleResponse;
import com.example.joynappclient.ui.booking.utils.StatusResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class BookingViewModel extends AndroidViewModel {
    private static final String TAG = "BookingViewModel";

    private MutableLiveData<CheckOutModel> checkOut = new MutableLiveData<>();
    private JoynRepository repository;
    private String addressLocation;
    private String cityName;
    private Context context;

    private MutableLiveData<Place> placeSearchDestination = new MutableLiveData<>();
    private MutableLiveData<HandleResponse<Place>> responsePickUp = new MutableLiveData<>();
    private MutableLiveData<HandleResponse<Place>> responseDestination = new MutableLiveData<>();


    public BookingViewModel(@NonNull Application application, JoynRepository repository) {
        super(application);
        this.context = application.getApplicationContext();
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

    public void setCheckOut(CheckOutModel checkOut) {
        this.checkOut.postValue(checkOut);
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

    public String getCity(String city) {
        Observable.just(city)
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    Log.d(TAG, "getCity: " + s);
                    String address = null;
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    try {
                        final List<Address> adresses = geocoder.getFromLocationName(s, 1);
                        address = adresses.get(0).getAddressLine(0);
                        Log.d(TAG, "getCity: " + adresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return address;
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                        cityName = s;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });

        return cityName;
    }

}
