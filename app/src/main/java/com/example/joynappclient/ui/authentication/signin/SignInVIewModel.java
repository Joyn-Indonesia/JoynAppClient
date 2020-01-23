package com.example.joynappclient.ui.authentication.signin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.data.JoynRepository;

public class SignInVIewModel extends ViewModel {

    private JoynRepository repository;

    public SignInVIewModel(JoynRepository repository) {
        this.repository = repository;
    }

    LiveData<Boolean> checkNumberPhone(String number, String reference) {
        MutableLiveData<Boolean> found = new MutableLiveData<>();

        repository.checkPhoneNumber(number, reference).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.getDocuments().isEmpty()) {
                found.setValue(false);
            } else {
                found.setValue(true);
            }
        });
        return found;
    }
}
