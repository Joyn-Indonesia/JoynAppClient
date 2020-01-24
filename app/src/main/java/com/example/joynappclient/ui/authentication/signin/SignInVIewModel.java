package com.example.joynappclient.ui.authentication.signin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.remote.ApiResponse;

public class SignInVIewModel extends ViewModel {

    private JoynRepository repository;

    private String phoneNumber;
    private String reference;

    public void setCredential(String phoneNumber, String reference) {
        this.phoneNumber = phoneNumber;
        this.reference = reference;
        checkNumberPhone();
    }

    public SignInVIewModel(JoynRepository repository) {
        this.repository = repository;
    }


    LiveData<ApiResponse> checkNumberPhone() {
        return repository.checkPhoneNumber(phoneNumber, reference);
    }
}
