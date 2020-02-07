package com.example.joynappclient.data.source.remote.firebase;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMinterface {
    @Headers({
            "Content-Type: application/json",
            "Authorization: key=AIzaSyCgJuIHUC4mutg5ahFsZW5VjUhCHwH5PQY"
    })
    @POST("send")
    Call<ResponseBody> sendRequest(@Body RequestBody body);
}
