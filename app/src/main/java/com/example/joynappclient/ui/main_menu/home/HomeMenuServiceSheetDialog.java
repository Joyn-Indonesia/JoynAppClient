package com.example.joynappclient.ui.main_menu.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeMenuServiceSheetDialog extends BottomSheetDialogFragment {
    private Context context;

    public HomeMenuServiceSheetDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_menu_content_service_all, container, false);
        ButterKnife.bind(this, v);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
    }

    @OnClick(R.id.menu_JRide)
    void moveRide() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JSend)
    void moveSend() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JCar)
    void moveCar() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JBox)
    void moveBox() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JFood)
    void moveFood() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JShoop)
    void moveShoop() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JMall)
    void moveMall() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                setupFullHeight(bottomSheetDialog);
            }
        });
        return dialog;
    }

    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels - 350;
        // return 1400;
    }
}
