package com.example.joynappclient.data;

import androidx.lifecycle.LiveData;

import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.firebase.FirebaseRepository;
import com.example.joynappclient.data.source.remote.gmaps.GmapRepository;
import com.example.joynappclient.data.source.remote.gmaps.model.ResponseGmaps;

public class JoynRepository implements JoynDataSource {
    private static volatile JoynRepository INSTANCE = null;

    private FirebaseRepository firebaseRepository;
    private GmapRepository gmapRepository;

    public JoynRepository(FirebaseRepository firebaseRepository, GmapRepository gmapRepository) {
        this.firebaseRepository = firebaseRepository;
        this.gmapRepository = gmapRepository;
    }

    public static JoynRepository getInstance(FirebaseRepository firebaseRepository, GmapRepository gmapRepository) {
        if (INSTANCE == null) {
            synchronized (JoynRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JoynRepository(firebaseRepository, gmapRepository);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<ApiResponse> checkPhoneNumber(String number, String reference) {
        return firebaseRepository.checkPhoneNumber(number, reference);
    }

    @Override
    public LiveData<ApiResponse<ResponseGmaps>> getAddress(String latLng, String apiKey) {
        return gmapRepository.getAdress(latLng, apiKey);
    }
}
