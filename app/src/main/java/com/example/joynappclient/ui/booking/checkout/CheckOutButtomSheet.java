package com.example.joynappclient.ui.booking.checkout;

import android.app.Dialog;
import android.content.Context;
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
    @BindView(R.id.tv_note)
    TextView noteCustomer;
    @BindView(R.id.tv_destintaion_text)
    TextView destinationAddress;
    @BindView(R.id.tv_ride_km)
    TextView rideKm;
    @BindView(R.id.tv_ride_minutes)
    TextView rideMinute;
    @BindView(R.id.tv_ride_price)
    TextView ridePrice;

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
    private Context context;

    public CheckOutButtomSheet(Context context) {
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelFactory factory = ViewModelFactory.getInstance(Objects.requireNonNull(getActivity()).getApplication());
        BookingViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(BookingViewModel.class);
        viewModel.getCheckOut().observe(getActivity(), model -> {
            pickUpAddress.setText(model.getPickupAdress());

            String note = "";
            if (model.getNote() != null) {
                note = model.getNote();
            }
            noteCustomer.setText("Note : " + note);
            destinationAddress.setText(model.getDestintaionAddress());
            rideKm.setText(model.getDistance());
            rideMinute.setText("+ " + model.getTimeDistance());
            ridePrice.setText(model.getCost());
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
