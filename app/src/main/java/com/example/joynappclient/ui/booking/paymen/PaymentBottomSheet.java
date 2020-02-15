package com.example.joynappclient.ui.booking.paymen;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.data.model.json.book.RequestRideCarRequestJson;
import com.example.joynappclient.data.source.remote.firebase.FCMservice;
import com.example.joynappclient.data.source.remote.firebase.model.FCMMessage;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.ui.inprogress.InProgressActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.example.joynappclient.utils.StringFormat;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentBottomSheet extends BottomSheetDialogFragment {
    private static final String TAG = "PaymentBottomSheet";

    @BindView(R.id.tv_distance_and_time)
    TextView distanceTime;
    @BindView(R.id.tv_discount_cost)
    TextView discountCost;
    @BindView(R.id.tv_total_cost)
    TextView totalCost;
    @BindView(R.id.btn_next)
    Button btnNextBooking;
    @BindView(R.id.extraSpace)
    View extraSpace;
    private View v;
    private RequestRideCarRequestJson requestJson;
    private BottomSheetBehavior bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_paymen_car, container, false);
        ButterKnife.bind(this, v);
        extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBottomSheet();
        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        BookingViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(BookingViewModel.class);
        viewModel.getRequestRideCar().observe(getActivity(), new Observer<RequestRideCarRequestJson>() {
            @Override
            public void onChanged(RequestRideCarRequestJson m) {
                requestJson = m;
                String info = "J-Ride " + m.getJarak() + " \u00B1 " + m.getWaktuPerjalanan();
                distanceTime.setText(info);
                discountCost.setText(" Discount : Rp. 0,-");
                totalCost.setText(StringFormat.costFormat(m.getHarga()));
            }
        });

        btnNextBooking.setOnClickListener(v -> {

            Gson gson = new Gson();
            FCMMessage message = new FCMMessage();
            message.setTo("fmCCYHw_rkc:APA91bGPs5Wesj1tOvkI5bbdEb8jSeU3n9XoZVGJqNR9JTlpSw-cJBpRyBOtcqkukW_DpRklDqtWh_K_VsP0PU7qZPA4LbrhFkMhU4hsS9MY2WjVbcygqZlMkF8afPzEG4c5gg6O0WBG");
            message.setData(requestJson);

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String json = gson.toJson(message);
            RequestBody body = RequestBody.create(json, JSON);

            Call<ResponseBody> booking = FCMservice.getInstance().sendRequest(body);
            booking.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d(TAG, "onResponse: ");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });

            moveInProgress();

        });
    }

    private void initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from((View) (v.getParent()));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (BottomSheetBehavior.STATE_HALF_EXPANDED == newState) {
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                }

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void moveInProgress() {
        MoveActivity.MoveAct(getActivity(), InProgressActivity.class);
        EventBus.getDefault().postSticky(requestJson);
        getActivity().finish();
    }
}
