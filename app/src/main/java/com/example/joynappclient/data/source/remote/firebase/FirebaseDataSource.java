package com.example.joynappclient.data.source.remote.firebase;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.model.ResponseUserLogin;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseDataSource {
    private static final String TAG = "FirebaseDataSource";

    private static volatile FirebaseDataSource INSTANCE = null;
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private ResponseUserLogin responseUserLogin;
    private Handler handler = new Handler();

    public static FirebaseDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseDataSource();
        }
        return INSTANCE;
    }


    public LiveData<ApiResponse> checkPhoneNumber(String number, String collection) {
        MutableLiveData<ApiResponse> checkNumber = new MutableLiveData<>();

        checkNumber.postValue(ApiResponse.loading("Loading...", null));

        CollectionReference reference = mDb.collection(collection);
        Query query = reference.whereEqualTo("phoneNumber", number);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();

                Log.d(TAG, "checkPhoneNumber: " + document.size());
                if (document.getDocuments().size() > 0) {
                    checkNumber.postValue(ApiResponse.success("sudah terdaftar", null));
                } else {
                    checkNumber.postValue(ApiResponse.empety("belum terdaftar", null));
                }
            } else {
                checkNumber.postValue(ApiResponse.error("Connectrion Error " + task.getException(), null));
            }
        });

        return checkNumber;
    }

}
