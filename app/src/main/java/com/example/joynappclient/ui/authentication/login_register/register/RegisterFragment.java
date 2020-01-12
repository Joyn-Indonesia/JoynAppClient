package com.example.joynappclient.ui.authentication.login_register.register;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.login_register.AuthenticationActivity;
import com.example.joynappclient.ui.authentication.login_register.sign_in.SignInFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "RegisterFragment";
    //widget
    @BindView(R.id.tv_signIn)
    TextView signIn;
    @BindView(R.id.btn_regiter)
    Button register;
    //vars
    private Context context;
    private AuthenticationActivity activity;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
        activity = new AuthenticationActivity();
        signIn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signIn:
            case R.id.btn_regiter:
                Fragment fragment = SignInFragment.getInstance();
                setFragment(fragment);
                break;


        }
    }

    private void setFragment(Fragment fragment) {
        (Objects.requireNonNull(getActivity())).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerMain, fragment, fragment.getClass().getSimpleName())
                .setCustomAnimations(R.animator.transition_animation_slide_up,
                        R.animator.transition_animation_slide_down,
                        R.animator.transition_animation_slide_up,
                        R.animator.transition_animation_slide_down)
                .show(fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();

    }
}
