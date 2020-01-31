package com.example.joynappclient.data;

import androidx.lifecycle.LiveData;

import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.gmaps.model.ResponseGmaps;

public interface JoynDataSource {

    LiveData<ApiResponse> checkPhoneNumber(String number, String reference);

    LiveData<ApiResponse<ResponseGmaps>> getAddress(String latLng, String apiKey);

}
