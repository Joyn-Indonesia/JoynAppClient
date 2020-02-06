package com.example.joynappclient.di;

import android.content.Context;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.data.source.local.LocalDataSource;
import com.example.joynappclient.data.source.local.room.JoynDataBase;
import com.example.joynappclient.data.source.remote.firebase.FirebaseDataSource;
import com.example.joynappclient.data.source.remote.gmaps.GmapDataSource;

public class DataInjection {

    public static JoynRepository provideRepository(Context context) {
        JoynDataBase joynDataBase = JoynDataBase.getInstance(context);
        FirebaseDataSource firebaseDataSource = FirebaseDataSource.getInstance();
        GmapDataSource gmapDataSource = GmapDataSource.getInstance();
        LocalDataSource localDataSource = LocalDataSource.getInstance(joynDataBase.joynDao());
        return JoynRepository.getInstance(context, firebaseDataSource, gmapDataSource, localDataSource);
    }
}
