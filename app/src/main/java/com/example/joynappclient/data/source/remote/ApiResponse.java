package com.example.joynappclient.data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.example.joynappclient.data.source.remote.StatusResponse.EMPTY;
import static com.example.joynappclient.data.source.remote.StatusResponse.ERROR;
import static com.example.joynappclient.data.source.remote.StatusResponse.LOADING;
import static com.example.joynappclient.data.source.remote.StatusResponse.SUCCESS;

public class ApiResponse<T> {

    @NonNull
    public final StatusResponse status;
    @NonNull
    public final String message;
    @NonNull
    public final T body;

    public ApiResponse(@NonNull StatusResponse status, @NonNull T body, @NonNull String message) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public static <T> ApiResponse<T> success(String message, @Nullable T body) {
        return new ApiResponse<>(SUCCESS, body, message);
    }

    public static <T> ApiResponse<T> empety(String message, @Nullable T body) {
        return new ApiResponse<>(EMPTY, body, message);
    }

    public static <T> ApiResponse<T> loading(String message, @Nullable T body) {
        return new ApiResponse<>(LOADING, body, message);
    }

    public static <T> ApiResponse<T> error(String message, @Nullable T body) {
        return new ApiResponse<>(ERROR, body, message);
    }


}
