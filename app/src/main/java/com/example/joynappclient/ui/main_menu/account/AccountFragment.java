package com.example.joynappclient.ui.main_menu.account;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.signin.SignInActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private static final String TAG = "AccountFragment";

    //widgets
    @BindView(R.id.tv_title_toolbar)
    TextView titleToolbar;
    @BindView(R.id.tv_profil_name)
    TextView profilUser;
    @BindView(R.id.tv_email)
    TextView emailUser;
    @BindView(R.id.tv_number_profil)
    TextView phoneUser;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment getInstance() {
        return new AccountFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleToolbar.setText("Account");
        getDetailUser();
    }

    @OnClick(R.id.btn_logout)
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        MoveActivity.MoveAct(getContext(), SignInActivity.class);
        getActivity().finish();
    }

    private void getDetailUser() {
        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        AccountFragmentViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(AccountFragmentViewModel.class);

        viewModel.getUserLogin().observe(getActivity(), user -> {
            profilUser.setText(user.getName());
            emailUser.setText(user.getEmail());
            phoneUser.setText(user.getPhoneNumber());
        });
    }
}
