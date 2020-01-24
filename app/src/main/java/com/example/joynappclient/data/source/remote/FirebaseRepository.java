package com.example.joynappclient.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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


    public LiveData<ApiResponse> checkPhoneNumber(String number, String collection) {
        MutableLiveData<ApiResponse> checkNumber = new MutableLiveData<>();

        checkNumber.postValue(ApiResponse.loading("Loading", null));

        CollectionReference reference = mDb.collection(collection);
        Query query = reference.whereEqualTo("phoneNumber", number);

        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.getDocuments().isEmpty()) {
                checkNumber.postValue(ApiResponse.empety("belum terdaftar", null));
            } else {
                checkNumber.postValue(ApiResponse.success("oke"));
            }
        }).addOnFailureListener(e -> checkNumber.postValue(ApiResponse.error("Problem Network", null)));


        return checkNumber;
    }

}
