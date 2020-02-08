package com.example.joynappclient.ui.booking.checkout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.ui.booking.checkout.model.CheckOutModel;
import com.example.joynappclient.ui.booking.paymen.PaymentBottomSheet;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckOut extends BottomSheetDialogFragment {
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
    @BindView(R.id.extraSpace)
    View extraSpace;
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
    private View view;
    private CheckOutModel checkOutModel;

    public CheckOut(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_bottom_sheet_check_out, container, false);
        ButterKnife.bind(this, view);
        extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.addBottomSheetCallback(callback);


        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        BookingViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(BookingViewModel.class);
        viewModel.getCheckOut().observe(getActivity(), model -> {

            checkOutModel = model;
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

    @OnClick(R.id.btn_select_service)
    public void sendBooking() {
        PaymentBottomSheet dialog = new PaymentBottomSheet();
        dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
        dismiss();
    }

}
