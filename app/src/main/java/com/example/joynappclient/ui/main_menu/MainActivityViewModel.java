package com.example.joynappclient.ui.main_menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.local.entity.UserLogin;

public class MainActivityViewModel extends ViewModel {

    private JoynRepository repository;

    public MainActivityViewModel(JoynRepository repository) {
        this.repository = repository;
    }

    LiveData<UserLogin> getUserLogin() {
        return repository.getUserLogin();
    }
}
