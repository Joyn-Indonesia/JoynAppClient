package com.example.joynappclient.ui.main_menu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.main_menu.home.HomeFragment;

import java.util.Objects;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        setFragment(HomeFragment.getInstance());

    }

    private void setFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fm.findFragmentByTag(tag) == null) {
            ft.add(R.id.containerMain, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        } else {
            ft.show(Objects.requireNonNull(fm.findFragmentByTag(tag))).commit();
        }
    }
}
