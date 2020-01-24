package com.example.joynappclient.data;

import androidx.lifecycle.LiveData;

import com.example.joynappclient.data.source.remote.ApiResponse;

public interface JoynDataSource {

    LiveData<ApiResponse> checkPhoneNumber(String number, String reference);

}
