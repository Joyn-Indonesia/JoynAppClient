package com.example.joynappclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.di.DataInjection;
import com.example.joynappclient.ui.authentication.otp.OtpVIewModel;
import com.example.joynappclient.ui.authentication.signin.SignInVIewModel;
import com.example.joynappclient.ui.authentication.signup.SignUpViewModel;
import com.example.joynappclient.ui.booking.BookingViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;
    private final JoynRepository joynRepository;
    private Application application;

    public ViewModelFactory(Application application, JoynRepository joynRepository) {
        this.joynRepository = joynRepository;
        this.application = application;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application, DataInjection.provideRepository(application));

                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SignInVIewModel.class)) {
            //noinspection unchecked
            return (T) new SignInVIewModel(joynRepository);
        } else if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            //noinspection unchecked
            return (T) new SignUpViewModel(joynRepository);
        } else if (modelClass.isAssignableFrom(OtpVIewModel.class)) {
            //noinspection unchecked
            return (T) new OtpVIewModel(joynRepository);
        } else if (modelClass.isAssignableFrom(BookingViewModel.class)) {
            //noinspection unchecked
            return (T) new BookingViewModel(application, joynRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel Class " + modelClass.getName());
    }
}
