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
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.application.JoynApp;
import com.example.joynappclient.data.source.local.entity.UserLogin;
import com.example.joynappclient.data.source.remote.firebase.FCMservice;
import com.example.joynappclient.data.source.remote.firebase.model.FCMMessage;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.ui.booking.checkout.model.CheckOutModel;
import com.example.joynappclient.ui.booking.checkout.model.DriverRequestModel;
import com.example.joynappclient.ui.inprogress.InProgressActivity;
import com.example.joynappclient.utils.MoveActivity;
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
    private CheckOutModel checkOutModel;
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
        viewModel.getCheckOut().observe(getActivity(), m -> {
            this.checkOutModel = m;
            String distaceTime = "J-Ride " + m.getDistance() + " \u00B1 " + m.getTimeDistance();
            distanceTime.setText(distaceTime);
            discountCost.setText(" Discount : Rp. 0,-");
            totalCost.setText(m.getCost());
        });

        btnNextBooking.setOnClickListener(v -> {
            UserLogin user = JoynApp.getInstance(getActivity().getApplicationContext()).getLoginUser();
            DriverRequestModel model = new DriverRequestModel();
            model.setUserName(user.getName());
            model.setRegIdPelanggan(user.getRegId());
            model.setPickupAddress(checkOutModel.getPickupAdress());
            model.setDestinationAddress(checkOutModel.getDestintaionAddress());
            model.setStartLatitude(checkOutModel.getPickupLatLg().latitude);
            model.setStartLongitude(checkOutModel.getPickupLatLg().longitude);
            model.setEndLatitude(checkOutModel.getDestinationLatLg().latitude);
            model.setEndLongitude(checkOutModel.getDestinationLatLg().longitude);
            model.setCost(checkOutModel.getCost());
            model.setDistance(checkOutModel.getDistance());

            Gson gson = new Gson();
            FCMMessage message = new FCMMessage();
            message.setTo("csIK9mRrIZQ:APA91bFm5HQ5TTNh7itdb_wVZh724PGxGpR_jLXOJu7wbdjfKkdBXJ07TrHJ5IjvkukdWHtMkhJsYPz0FSv0fbYHhmV58oecYbzzhO-fa6CGYTYaFOJzSW89rjc9XHIo-pLDJyh1KL4A");
            message.setData(model);

            MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");

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
                if (BottomSheetBehavior.STATE_HALF_EXPANDED == newState) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
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
        EventBus.getDefault().postSticky(checkOutModel);
        getActivity().finish();
    }
}
