package com.example.joynappclient.data.source.remote.gmaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.joynappclient.data.source.remote.ApiResponse;
import com.example.joynappclient.data.source.remote.gmaps.model.ResponseGmaps;
import com.example.joynappclient.data.source.remote.gmaps.network.InitRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class GmapDataSource {

    private static volatile GmapDataSource INSTANCE;
    private ResponseGmaps gmaps;

    public static GmapDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (GmapDataSource.class) {
                INSTANCE = new GmapDataSource();
            }
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<ResponseGmaps>> getAdress(String latLng, String apikey) {
        MutableLiveData<ApiResponse<ResponseGmaps>> adress = new MutableLiveData<>();

        Observable<ResponseGmaps> getAddress = InitRetrofit.getInstance().getAddress(latLng, apikey);
        getAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ResponseGmaps>() {
                    @Override
                    public void onNext(ResponseGmaps responseGmaps) {
                        if (responseGmaps != null) {
                            gmaps = responseGmaps;
                        } else {
                            adress.postValue(ApiResponse.empety("null", null));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        adress.postValue(ApiResponse.error(e.toString(), null));
                    }

                    @Override
                    public void onComplete() {
                        adress.postValue(ApiResponse.success(null, gmaps));
                    }
                });

        return adress;
    }

}
