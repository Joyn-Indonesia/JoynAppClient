package com.example.joynappclient.ui.main_menu.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.local.entity.LocalUserLogin;

public class AccountFragmentViewModel extends ViewModel {

    private JoynRepository repository;

    public AccountFragmentViewModel(JoynRepository repository) {
        this.repository = repository;
    }

    LiveData<LocalUserLogin> getUserLogin() {
        return repository.getUserLogin();
    }
}
