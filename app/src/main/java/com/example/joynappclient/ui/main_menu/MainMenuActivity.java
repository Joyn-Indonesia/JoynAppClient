package com.example.joynappclient.ui.main_menu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.joynappclient.R;
import com.example.joynappclient.ui.main_menu.account.AccountFragment;
import com.example.joynappclient.ui.main_menu.chat.ChatFragment;
import com.example.joynappclient.ui.main_menu.home.HomeFragment;
import com.example.joynappclient.ui.main_menu.inbox.InboxFragment;
import com.example.joynappclient.ui.main_menu.order.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity {

    public static final int MENU_HOME = 1;
    public static final int MENU_ORDER = 2;
    public static final int MENU_CHAT = 3;
    public static final int MENU_INBOX = 4;
    public static final int MENU_ACCOUNT = 5;

    //widget
    @BindView(R.id.meowBottomNavigation)
    MeowBottomNavigation navView;

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

        navView.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case MENU_HOME:
                    setFragment(HomeFragment.getInstance());
                    break;
                case MENU_ORDER:
                    setFragment(OrderFragment.getInstance());
                    break;
                case MENU_CHAT:
                    setFragment(ChatFragment.getInstance());
                    break;
                case MENU_INBOX:
                    setFragment(InboxFragment.getInstance());
                    break;
                case MENU_ACCOUNT:
                    setFragment(AccountFragment.getInstance());
                    break;
            }
            return null;
        });

        navView.show(MENU_HOME, true);
        navView.setCount(MENU_INBOX, "9");
        setFragment(HomeFragment.getInstance());

    }

    private void setFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerMain, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
//        if (fm.findFragmentByTag(tag) == null) {
//            ft.add(R.id.containerMain, fragment, tag)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .addToBackStack(tag)
//                    .commit();
//        } else {
//            ft.show(Objects.requireNonNull(fm.findFragmentByTag(tag))).commit();
//        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            super.onBackPressed();
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
