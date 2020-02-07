package com.example.joynappclient.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.data.JoynRepository;
import com.example.joynappclient.di.DataInjection;
import com.example.joynappclient.ui.authentication.otp.OtpVIewModel;
import com.example.joynappclient.ui.authentication.signin.SignInVIewModel;
import com.example.joynappclient.ui.authentication.signup.SignUpViewModel;
import com.example.joynappclient.ui.authentication.welcome_to_app.WelcomeViewModel;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.ui.main_menu.MainMenuViewModel;
import com.example.joynappclient.ui.main_menu.account.AccountFragmentViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;
    private final JoynRepository joynRepository;
    private Context context;

    public ViewModelFactory(Context context, JoynRepository joynRepository) {
        this.joynRepository = joynRepository;
        this.context = context;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(context, DataInjection.provideRepository(context));
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
        } else if (modelClass.isAssignableFrom(WelcomeViewModel.class)) {
            //noinspection unchecked
            return (T) new WelcomeViewModel(joynRepository);
        } else if (modelClass.isAssignableFrom(MainMenuViewModel.class)) {
            //noinspection unchecked
            return (T) new MainMenuViewModel(joynRepository);
        } else if (modelClass.isAssignableFrom(BookingViewModel.class)) {
            //noinspection unchecked
            return (T) new BookingViewModel(context, joynRepository);
        } else if (modelClass.isAssignableFrom(AccountFragmentViewModel.class)) {
            //noinspection unchecked
            return (T) new AccountFragmentViewModel(joynRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel Class " + modelClass.getName());
    }
}
