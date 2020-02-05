package com.example.joynappclient.ui.booking.checkout;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutButtomSheet extends BottomSheetDialogFragment {
    private static final String TAG = "CheckOutButtomSheetDial";

    @BindView(R.id.tv_pickUp_text)
    TextView pickUpAddress;
    @BindView(R.id.tv_destintaion_text)
    TextView destinationAddress;
    @BindView(R.id.tv_detail_transaction)
    TextView detailTransaction;

    private BottomSheetBehavior bottomSheetBehavior;

    BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            if (BottomSheetBehavior.STATE_HIDDEN == newState) {

                dismiss();
            }

            Log.d(TAG, "onStateChanged: " + newState);
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Log.d(TAG, "BottomSheetCallback " + "slideOffset: " + slideOffset);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelFactory factory = ViewModelFactory.getInstance(Objects.requireNonNull(getActivity()).getApplication());
        BookingViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(BookingViewModel.class);
        viewModel.getCheckOut().observe(getActivity(), model -> {
            pickUpAddress.setText(model.getPickupAdress());
            destinationAddress.setText(model.getDestintaionAddress());
            detailTransaction.setText(String.format(getString(R.string.text_ride_detail),
                    model.getDistance(),
                    model.getTimeDistance(), model.getCost()));
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet_check_out, null);

        ButterKnife.bind(this, view);

        bottomSheet.setContentView(view);
        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setBottomSheetCallback(callback);
        return bottomSheet;

    }
}
