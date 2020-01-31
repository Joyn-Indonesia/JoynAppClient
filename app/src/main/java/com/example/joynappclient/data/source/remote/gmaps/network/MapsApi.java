package com.example.joynappclient.data.source.remote.gmaps.network;

import com.example.joynappclient.data.source.remote.gmaps.model.ResponseGmaps;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MapsApi {

    @GET("geocode/json")
    Observable<ResponseGmaps> getAddress(@Query("latlng") String latLng,
                                         @Query("key") String apiKey);

    // @GET("directions/json")


}
