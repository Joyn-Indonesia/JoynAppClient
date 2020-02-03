package com.example.joynappclient.ui.booking.checkout;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutButtomSheetDialog extends BottomSheetDialogFragment {

    @BindView(R.id.tv_pickUp_text)
    TextView pickUpAddress;
    @BindView(R.id.tv_destintaion_text)
    TextView destinationAddress;
    @BindView(R.id.tv_detail_transaction)
    TextView detailTransaction;

    private BookingViewModel viewModel;

    private CheckOutModel s;

    public CheckOutButtomSheetDialog(CheckOutModel outModel) {
        this.s = outModel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_check_out, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        ViewModelFactory factory = ViewModelFactory.getInstance();
//         viewModel = new ViewModelProvider(getParentFragment(), factory).get(BookingViewModel.class);

//        viewModel.getCheckOut().observe(getViewLifecycleOwner(), s -> {
        pickUpAddress.setText(s.getPickupAdress());
        destinationAddress.setText(s.getDestintaionAddress());
        String detail = String.format(getString(R.string.text_ride_detail), s.getDistance(), s.getTimeDistance(), s.getCost());
        detailTransaction.setText(detail);
//        });
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);

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
        return displayMetrics.heightPixels;
        // return 1400;
    }
}
