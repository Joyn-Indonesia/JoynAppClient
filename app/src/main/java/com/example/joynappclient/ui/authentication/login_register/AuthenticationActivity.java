package com.example.joynappclient.ui.authentication.login_register;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.authentication.login_register.register.RegisterFragment;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        populateFragment();

    }

    private void populateFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = RegisterFragment.newInstance();
        fragmentTransaction.replace(R.id.containerMain, fragment, fragment.getClass().getSimpleName());

        fragmentTransaction.setCustomAnimations(R.animator.transition_animation_slide_up,
                R.animator.transition_animation_slide_down,
                R.animator.transition_animation_slide_up,
                R.animator.transition_animation_slide_down)
                .show(fragment);

        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
            return;
        }
        super.onBackPressed();

    }
}
