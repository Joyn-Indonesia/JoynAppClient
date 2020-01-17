package com.example.joynappclient.ui.authentication.login_register.sign_in;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.login_register.register.RegisterFragment;
import com.example.joynappclient.ui.authentication.otp.OtpActivity;
import com.example.joynappclient.utils.MoveActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SignInFragment";
    String[] COUNTRIES = new String[]{"+62", "+63", "+64"};
    //widget
    @BindView(R.id.tv_signUp)
    TextView signUp;
    @BindView(R.id.btn_call)
    Button call;
    @BindView(R.id.btn_sms)
    Button sms;
    @BindView(R.id.btn_whatsapp)
    Button whatsapp;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    //var
    private Context context;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        editTextFilledExposedDropdown = view.findViewById(R.id.languange_drodown);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getContext();

        signUp.setOnClickListener(this);
        call.setOnClickListener(this);
        sms.setOnClickListener(this);
        whatsapp.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, COUNTRIES
        );
        editTextFilledExposedDropdown.setText(COUNTRIES[0]);
        editTextFilledExposedDropdown.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signUp:
                setFragment(RegisterFragment.newInstance());
                Log.d(TAG, "onClick: ");
                break;

            case R.id.btn_call:
                MoveActivity.MoveAct(context, OtpActivity.class);
                break;

            case R.id.btn_sms:
                MoveActivity.MoveAct(context, OtpActivity.class);
                break;

            case R.id.btn_whatsapp:
                MoveActivity.MoveAct(context, OtpActivity.class);
                break;

        }
    }

    private void setFragment(Fragment fragment) {
        Log.d(TAG, "setFragment: ");
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        if (fm.findFragmentByTag(fragment.getClass().getSimpleName()) == null) {
            fm.beginTransaction().replace(R.id.containerMain, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            Log.d(TAG, "setFragment:1 ");
        } else {
            fm.popBackStack();
            Log.d(TAG, "setFragment: 2");
        }
    }
}
