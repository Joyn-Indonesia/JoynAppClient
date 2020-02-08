package com.example.joynappclient.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.joynappclient.ui.inprogress.DriverModel;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingServic";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (!remoteMessage.getData().isEmpty()) {

            Map<String, String> data = remoteMessage.getData();
            Gson gson = new Gson();
            String json = gson.toJson(data);
            Log.d(TAG, "onMessageReceived: " + json);
            DriverModel model = gson.fromJson(json, DriverModel.class);
            EventBus.getDefault().post(model);
        }
    }

}
