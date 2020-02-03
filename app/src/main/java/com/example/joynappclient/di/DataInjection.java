package com.example.joynappclient.di;

import android.app.Application;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.remote.firebase.FirebaseRepository;
import com.example.joynappclient.data.source.remote.gmaps.GmapRepository;

public class DataInjection {

    public static JoynRepository provideRepository(Application application) {
        Application application1 = application;
        FirebaseRepository firebaseRepository = FirebaseRepository.getInstance();
        GmapRepository gmapRepository = GmapRepository.getInstance();
        return JoynRepository.getInstance(application, firebaseRepository, gmapRepository);
    }
}
