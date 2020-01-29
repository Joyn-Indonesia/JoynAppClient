package com.example.joynappclient.ui.main_menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.joynappclient.R;
import com.example.joynappclient.application.JoynApp;
import com.example.joynappclient.data.source.remote.model.UserModel;
import com.example.joynappclient.ui.main_menu.account.AccountFragment;
import com.example.joynappclient.ui.main_menu.adapter.VIewPagerAdapter;
import com.example.joynappclient.ui.main_menu.chat.ChatFragment;
import com.example.joynappclient.ui.main_menu.home.HomeFragment;
import com.example.joynappclient.ui.main_menu.inbox.InboxFragment;
import com.example.joynappclient.ui.main_menu.order.OrderFragment;
import com.example.joynappclient.utils.Constant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.joynappclient.utils.Constant.ERROR_DIALOG_REQUEST;
import static com.example.joynappclient.utils.Constant.PERMISSIONS_REQUEST_ENABLE_GPS;

public class MainMenuActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

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
    private boolean mPermissionService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ButterKnife.bind(this);

        requestPermissions();

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
        setFragment(HomeFragment.getInstance());

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

    private void setFragment(Fragment fragment) {
//        String tag = fragment.getClass().getSimpleName();
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.containerMain, fragment, tag)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit();
//        if (fm.findFragmentByTag(tag) == null) {
//            ft.add(R.id.containerMain, fragment, tag)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .addToBackStack(tag)
//                    .commit();
//        } else {
//            ft.show(Objects.requireNonNull(fm.findFragmentByTag(tag))).commit();
//        }
    }

    private void getUserDetail() {
        FirebaseFirestore mDb = FirebaseFirestore.getInstance();
        DocumentReference reffUser = mDb.collection(getString(R.string.collection_users)).document(FirebaseAuth.getInstance().getUid());

        reffUser.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    Log.d(TAG, "onComplete: get user complete");

                    UserModel user = task.getResult().toObject(UserModel.class);
                    Log.d(TAG, "onComplete: " + user.getName());
                    ((JoynApp) getApplicationContext()).setUser(user);

                }
            }
        });

    }

    private boolean checkMapServices() {
        if (isServicesOK()) {
            return isMapsEnabled();
        }
        return false;
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainMenuActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainMenuActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean isMapsEnabled() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private boolean hasLocationPermission() {
        return EasyPermissions.hasPermissions(this, Constant.PERMISSIONS);
    }

    @AfterPermissionGranted(Constant.REQUEST_PERMISSIONS)
    private void requestPermissions() {

        if (hasLocationPermission()) {
            mPermissionService = true;
            Log.d(TAG, "requestPermissions: acces location");
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "OKE",
                    Constant.REQUEST_PERMISSIONS, Constant.PERMISSIONS);
        }

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

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
        if (checkMapServices()) {
            if (!mPermissionService) {
                requestPermissions();
            }
        }
    }
}
