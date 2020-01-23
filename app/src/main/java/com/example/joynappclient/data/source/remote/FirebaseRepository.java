package com.example.joynappclient.data.source.remote;

import android.os.Handler;
import android.util.Log;

import com.example.joynappclient.data.source.remote.model.UserModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirebaseRepository {
    private static final String TAG = "FirebaseRepository";

    private static volatile FirebaseRepository INSTANCE = null;
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private UserModel userModel;
    private Handler handler = new Handler();

    public static FirebaseRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository();
        }
        return INSTANCE;
    }

    public Query checkPhoneNumber(String number, String collection) {
        Log.d(TAG, "checkPhoneNumber: ");
        CollectionReference reference = mDb.collection(collection);
        Query query = reference.whereEqualTo("phoneNumber", number);

        return query;
    }


}
