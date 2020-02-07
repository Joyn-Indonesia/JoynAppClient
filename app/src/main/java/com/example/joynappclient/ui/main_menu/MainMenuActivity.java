package com.example.joynappclient.ui.main_menu;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.joynappclient.R;
import com.example.joynappclient.application.JoynApp;
import com.example.joynappclient.ui.main_menu.account.AccountFragment;
import com.example.joynappclient.ui.main_menu.adapter.VIewPagerAdapter;
import com.example.joynappclient.ui.main_menu.chat.ChatFragment;
import com.example.joynappclient.ui.main_menu.home.HomeFragment;
import com.example.joynappclient.ui.main_menu.inbox.InboxFragment;
import com.example.joynappclient.ui.main_menu.order.OrderFragment;
import com.example.joynappclient.utils.BaseActivity;
import com.example.joynappclient.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends BaseActivity {

    //bottom Bar
    public static final int MENU_HOME = 1;
    public static final int MENU_ORDER = 2;
    public static final int MENU_CHAT = 3;
    public static final int MENU_INBOX = 4;
    public static final int MENU_ACCOUNT = 5;
    private static final String TAG = "MainMenuActivity";

    //widget
    @BindView(R.id.meowBottomNavigation)
    MeowBottomNavigation navView;
    @BindView(R.id.containerMain)
    ViewPager container;

    //vars
    boolean doubleBackToExitPressedOnce = false;
    boolean showloading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ButterKnife.bind(this);


        navView.add(new MeowBottomNavigation.Model(MENU_HOME, R.drawable.icon_home));
        navView.add(new MeowBottomNavigation.Model(MENU_ORDER, R.drawable.icon_order));
        navView.add(new MeowBottomNavigation.Model(MENU_CHAT, R.drawable.icon_chat));
        navView.add(new MeowBottomNavigation.Model(MENU_INBOX, R.drawable.icon_inbox));
        navView.add(new MeowBottomNavigation.Model(MENU_ACCOUNT, R.drawable.icon_account));

        initFragment();

        navView.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case MENU_HOME:
                    container.setCurrentItem(0);
                    break;
                case MENU_ORDER:
                    container.setCurrentItem(1);
                    break;
                case MENU_CHAT:
                    container.setCurrentItem(2);
                    break;
                case MENU_INBOX:
                    container.setCurrentItem(3);
                    break;
                case MENU_ACCOUNT:
                    container.setCurrentItem(4);
                    break;
            }
            return null;
        });

        navView.show(MENU_HOME, true);
        navView.setCount(MENU_INBOX, "9");
    }

    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.getInstance());
        fragments.add(OrderFragment.getInstance());
        fragments.add(ChatFragment.getInstance());
        fragments.add(InboxFragment.getInstance());
        fragments.add(AccountFragment.getInstance());

        VIewPagerAdapter adapter = new VIewPagerAdapter(getSupportFragmentManager(), fragments);
        container.setAdapter(adapter);
        container.setCurrentItem(0);
        container.setOffscreenPageLimit(5);
    }

    private void getDetailUser() {
        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        MainMenuViewModel viewModel = new ViewModelProvider(this, factory).get(MainMenuViewModel.class);

        viewModel.getUserLogin().observe(this, userLogin -> {
            Log.d(TAG, "getDetailUser: " + userLogin.getName());
            JoynApp.getInstance(this).setLoginUser(userLogin);
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDetailUser();
        if (checkMapServices()) {
            if (!mPermissionService) {
                requestPermissions();
            }
        }
    }
}
