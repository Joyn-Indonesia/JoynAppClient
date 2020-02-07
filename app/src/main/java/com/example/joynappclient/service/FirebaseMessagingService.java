package com.example.joynappclient.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingServic";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


//        String data = remoteMessage.getNotification().getBody()+" "+ remoteMessage.getData().get("body");

        Log.d(TAG, "onMessageReceived: ");
        EventBus.getDefault().post("data");
    }

}
