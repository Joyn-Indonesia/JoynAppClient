package com.example.joynappclient.ui.inprogress;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.checkout.model.CheckOutModel;
import com.example.joynappclient.utils.BaseActivity;
import com.example.joynappclient.utils.VectorDescriptor;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InProgressActivity extends BaseActivity implements OnMapReadyCallback {
    private static final String TAG = "InProgressActivity";
    @BindView(R.id.bottom_sheet_driver)
    ConstraintLayout bottomsheet;
    private GoogleMap mMap;
    private Marker pickUpMarker;
    private Marker destinationMarker;
    private Marker Driver;
    private InProgressViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_progress);
        ButterKnife.bind(this);
        bottomsheet.setVisibility(View.GONE);

        viewModel = new ViewModelProvider(this).get(InProgressViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);

        viewModel.getCheckOut().observe(this, model -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(model.getPickupLatLg(), 10));
            updateCameraBounds(model);
            createMarker(model);
            updateLineDestination(model);
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);

    }

    private void updateLineDestination(CheckOutModel model) {
        if (model != null) {
            Log.d(TAG, "updateLineDestination: ");
            new Handler(getMainLooper()).post(() -> {

                List<LatLng> newDecodedPath = new ArrayList<>();
                for (com.google.maps.model.LatLng latLng : model.getEncodedPolyline().decodePath()) {
                    newDecodedPath.add(new LatLng(latLng.lat, latLng.lng));
                }

                mMap.addPolyline((new PolylineOptions())
                        .addAll(newDecodedPath)
                        .color(getResources().getColor(R.color.greenDarkerMain))
                        .width(17));
            });
        }
    }


    private void createMarker(CheckOutModel model) {
        if (pickUpMarker != null) pickUpMarker.remove();
        if (destinationMarker != null) destinationMarker.remove();

        pickUpMarker = mMap.addMarker(new MarkerOptions()
                .position(model.getPickupLatLg())
                .title("Start")
                .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));

        destinationMarker = mMap.addMarker(new MarkerOptions()
                .position(model.getDestinationLatLg())
                .title("End")
                .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_destination)));

    }

    public void updateCameraBounds(CheckOutModel model) {
        Log.d(TAG, "updateCameraBounds: " + model.getLatLngBounds().southwest);
        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(model.getLatLngBounds(), 600, 400, 100);
        mMap.moveCamera(update);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventReceived(CheckOutModel model) {
        viewModel.setCheckOut(model);
        EventBus.getDefault().removeStickyEvent(CheckOutModel.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDriverEvent(DriverModel model) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(model.getLatitude(), model.getLongitude()))
                .title("Driver")
                .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_ride)));

        bottomsheet.setVisibility(View.VISIBLE);
        TextView namadriver = findViewById(R.id.tv_nama_driver);
        namadriver.setText(model.getName());

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
