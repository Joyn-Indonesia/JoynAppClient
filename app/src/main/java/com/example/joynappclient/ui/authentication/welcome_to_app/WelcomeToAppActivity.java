package com.example.joynappclient.ui.authentication.welcome_to_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.application.JoynApp;
import com.example.joynappclient.data.source.local.entity.LocalUserLogin;
import com.example.joynappclient.data.source.remote.model.ResponseUserLogin;
import com.example.joynappclient.ui.main_menu.MainMenuActivity;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeToAppActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeToAppActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to_app);
        ButterKnife.bind(this);
        getUserDetail();
    }

    @OnClick(R.id.btn_tryOur)
    public void MainMenu() {
        startActivity(new Intent(WelcomeToAppActivity.this, MainMenuActivity.class));
        finishAffinity();
    }

    private void getUserDetail() {

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        WelcomeViewModel viewModel = new ViewModelProvider(this, factory).get(WelcomeViewModel.class);

        viewModel.deleteUserLogin();

        FirebaseFirestore mDb = FirebaseFirestore.getInstance();
        DocumentReference reffUser = mDb.collection(getString(R.string.collection_users)).document(FirebaseAuth.getInstance().getUid());

        reffUser.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                Log.d(TAG, "onComplete: get user complete");

                String token = JoynApp.getInstance(this).getToken();
                ResponseUserLogin responseUserLogin = task.getResult().toObject(ResponseUserLogin.class);

                LocalUserLogin localUserLogin = new LocalUserLogin();
                localUserLogin.setNamaDepan(responseUserLogin.getNamaDepan());
                localUserLogin.setNoTelepon(responseUserLogin.getNoTelepon());
                localUserLogin.setEmail(responseUserLogin.getEmail());
                localUserLogin.setId(responseUserLogin.getId());
                localUserLogin.setRegId(responseUserLogin.getRegId());

                if (responseUserLogin.getRegId() == null || !responseUserLogin.getRegId().equals(token)) {
                    Log.d(TAG, "getUserDetail: token not macth");
                    responseUserLogin.setRegId(token);
                    mDb.collection(getString(R.string.collection_users)).document(FirebaseAuth.getInstance().getUid())
                            .set(responseUserLogin);
                    localUserLogin.setRegId(responseUserLogin.getRegId());
                }
                viewModel.saveUserLogin(localUserLogin);
                JoynApp.getInstance(this).setLoginUser(localUserLogin);
            } else {
                Log.d(TAG, "getUserDetail: failed");
            }
        });
    }
}
