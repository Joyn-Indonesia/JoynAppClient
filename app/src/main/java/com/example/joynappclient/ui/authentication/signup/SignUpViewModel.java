package com.example.joynappclient.ui.authentication.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.model.ResponseUserLogin;

public class SignUpViewModel extends ViewModel {
    private JoynRepository repository;

    private String phoneNumber;
    private String reference;
    private ResponseUserLogin responseUserLogin;

    public SignUpViewModel(JoynRepository repository) {
        this.repository = repository;
    }

    public void setCredential(String phoneNumber, String reference) {
        this.phoneNumber = phoneNumber;
        this.reference = reference;
    }

    public void setResponseUserLogin(ResponseUserLogin responseUserLogin) {
        this.responseUserLogin = responseUserLogin;
    }

    public LiveData<ApiResponse> checkPhoneNumber() {
        return repository.checkPhoneNumber(phoneNumber, reference);
    }

}
