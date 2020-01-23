package com.example.joynappclient.di;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.remote.FirebaseRepository;

public class DataInjection {

    public static JoynRepository provideRepository() {
        FirebaseRepository firebaseRepository = FirebaseRepository.getInstance();
        return JoynRepository.getInstance(firebaseRepository);
    }
}
