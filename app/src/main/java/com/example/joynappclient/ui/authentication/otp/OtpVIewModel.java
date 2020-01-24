package com.example.joynappclient.ui.authentication.otp;

import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;

public class OtpVIewModel extends ViewModel {

    private JoynRepository repository;


    public OtpVIewModel(JoynRepository repository) {
        this.repository = repository;
    }


}
