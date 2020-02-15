package com.example.joynappclient.data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.joynappclient.data.source.local.LocalDataSource;
import com.example.joynappclient.data.source.local.entity.LocalUserLogin;
import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.firebase.FirebaseDataSource;
import com.example.joynappclient.data.source.remote.gmaps.GmapDataSource;
import com.example.joynappclient.data.source.remote.gmaps.model.ResponseGmaps;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JoynRepository implements JoynDataSource {
    private static final String TAG = "JoynRepository";
    private static volatile JoynRepository INSTANCE = null;

    private FirebaseDataSource firebaseDataSource;
    private GmapDataSource gmapDataSource;
    private LocalDataSource localDataSource;
    private Context context;

    public JoynRepository(Context context, FirebaseDataSource firebaseDataSource, GmapDataSource gmapDataSource, LocalDataSource localDataSource) {
        this.firebaseDataSource = firebaseDataSource;
        this.gmapDataSource = gmapDataSource;
        this.localDataSource = localDataSource;
        this.context = context;
    }

    public static JoynRepository getInstance(Context context, FirebaseDataSource firebaseDataSource, GmapDataSource gmapDataSource, LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (JoynRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JoynRepository(context, firebaseDataSource, gmapDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<ApiResponse> checkPhoneNumber(String number, String reference) {
        return firebaseDataSource.checkPhoneNumber(number, reference);
    }

    @Override
    public LiveData<ApiResponse<ResponseGmaps>> getAddress(String latLng, String apiKey) {
        return gmapDataSource.getAdress(latLng, apiKey);
    }

    @Override
    public LiveData<LocalUserLogin> getUserLogin() {
        return localDataSource.getUserLogin();
    }

    @Override
    public void saveUserLogin(LocalUserLogin localUserLogin) {
        Log.d(TAG, "saveUserLogin: save ");
        Completable.fromAction(() -> localDataSource.insertUserLogin(localUserLogin)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void updateUserLogin(LocalUserLogin localUserLogin) {
        Completable.fromAction(() -> localDataSource.updateUserLogin(localUserLogin)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    public void deleteUserLogin() {
        Log.d(TAG, "deleteUserLogin: delete");
        Completable.fromAction(() -> localDataSource.deleteUserLogin()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
