package com.example.joynappclient.viewmodel;

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

    public ViewModelFactory(JoynRepository joynRepository) {
        this.joynRepository = joynRepository;
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(DataInjection.provideRepository());
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
            return (T) new BookingViewModel(joynRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel Class " + modelClass.getName());
    }
}
