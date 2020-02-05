package com.example.joynappclient.ui.booking.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.example.joynappclient.ui.booking.utils.StatusResponse.PICKS;
import static com.example.joynappclient.ui.booking.utils.StatusResponse.SEARCH;

public class HandleResponse<T> {

    @NonNull
    public final StatusResponse status;

    @NonNull
    public final T body;

    public HandleResponse(@NonNull StatusResponse status, @NonNull T body) {
        this.status = status;
        this.body = body;
    }

    public static <T> HandleResponse<T> search(T body) {
        return new HandleResponse<>(SEARCH, body);
    }

    public static <T> HandleResponse<T> picks(@Nullable T body) {
        return new HandleResponse<>(PICKS, body);
    }
}
