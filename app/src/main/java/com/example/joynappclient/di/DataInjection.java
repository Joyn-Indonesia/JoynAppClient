package com.example.joynappclient.di;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.remote.firebase.FirebaseRepository;
import com.example.joynappclient.data.source.remote.gmaps.GmapRepository;

public class DataInjection {

    public static JoynRepository provideRepository() {
        FirebaseRepository firebaseRepository = FirebaseRepository.getInstance();
        GmapRepository gmapRepository = GmapRepository.getInstance();
        return JoynRepository.getInstance(firebaseRepository, gmapRepository);
    }
}
