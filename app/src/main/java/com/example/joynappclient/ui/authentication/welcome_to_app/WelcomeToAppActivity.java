package com.example.joynappclient.ui.authentication.welcome_to_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.application.JoynApp;
import com.example.joynappclient.data.source.local.entity.UserLogin;
import com.example.joynappclient.data.source.remote.model.UserModel;
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
                UserModel user = task.getResult().toObject(UserModel.class);

                UserLogin userLogin = new UserLogin();
                userLogin.setName(user.getName());
                userLogin.setPhoneNumber(user.getPhoneNumber());
                userLogin.setEmail(user.geteMail());
                userLogin.setUserId(user.getUserId());
                userLogin.setRegId(user.getRegId());

                if (user.getRegId() == null || !user.getRegId().equals(token)) {
                    Log.d(TAG, "getUserDetail: token not macth");
                    user.setRegId(token);
                    mDb.collection(getString(R.string.collection_users)).document(FirebaseAuth.getInstance().getUid())
                            .set(user);
                    userLogin.setRegId(user.getRegId());
                }
                viewModel.saveUserLogin(userLogin);
                JoynApp.getInstance(this).setLoginUser(userLogin);
            } else {
                Log.d(TAG, "getUserDetail: failed");
            }
        });
    }
}
