package com.example.joynappclient.ui.booking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.gmaps.model.ResponseGmaps;

public class BookingViewModel extends ViewModel {
    private JoynRepository repository;


    private String latLg;
    private String apiKey;
    private LiveData<ApiResponse<ResponseGmaps>> address;

    public BookingViewModel(JoynRepository joynRepository) {
    }


    public void setDataAddress(String latLg, String apiKey) {
        getAdress(latLg, apiKey);
    }

    LiveData<ApiResponse<ResponseGmaps>> getAdress(String latLg, String apiKey) {
        return address = repository.getAddress(latLg, apiKey);
    }

    public LiveData<ApiResponse<ResponseGmaps>> getAdress() {
        return address;
    }
}
