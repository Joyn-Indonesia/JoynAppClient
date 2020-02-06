package com.example.joynappclient.ui.authentication.welcome_to_app;

import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.local.entity.UserLogin;

public class WelcomeViewModel extends ViewModel {

    private JoynRepository repository;

    public WelcomeViewModel(JoynRepository repository) {
        this.repository = repository;
    }

    public void saveUserLogin(UserLogin userLogin) {
        repository.saveUserLogin(userLogin);
    }

    public void deleteUserLogin() {
        repository.deleteUserLogin();
    }

}
