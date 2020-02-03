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
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.observers.DefaultObserver;

public class BookingViewModel extends AndroidViewModel {
    private static final String TAG = "BookingViewModel";

    private MutableLiveData<CheckOutModel> checkOut = new MutableLiveData<>();
    private JoynRepository repository;
    private MutableLiveData<Address> addressLocation = new MutableLiveData<>();
    private Context context;

    public BookingViewModel(@NonNull Application application, JoynRepository repository) {
        super(application);
        this.context = application.getApplicationContext();
        this.repository = repository;
    }

    public LiveData<CheckOutModel> getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(CheckOutModel checkOut) {
        this.checkOut.postValue(checkOut);
    }

    public LiveData<Address> getAdress(LatLng latLng) {
        Observable<LatLng> address = Observable.just(latLng);
        address.subscribe(new DefaultObserver<LatLng>() {
            @Override
            public void onNext(LatLng latLng) {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                try {
                    final List<Address> adresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (!adresses.isEmpty()) {
                        Log.d(TAG, "onNext: " + adresses.size());
                        addressLocation.postValue(adresses.get(0));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        return addressLocation;
    }
}
