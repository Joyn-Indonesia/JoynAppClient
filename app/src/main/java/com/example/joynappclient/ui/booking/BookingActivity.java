package com.example.joynappclient.ui.booking;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.data.gmap.directions.Directions;
import com.example.joynappclient.data.gmap.directions.Route;
import com.example.joynappclient.ui.booking.api.MapDirectionAPI;
import com.example.joynappclient.ui.booking.checkout.CheckOutButtomSheetDialog;
import com.example.joynappclient.ui.booking.checkout.CheckOutModel;
import com.example.joynappclient.utils.BaseActivity;
import com.example.joynappclient.utils.Constant;
import com.example.joynappclient.utils.VectorDescriptor;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingActivity extends BaseActivity implements OnMapReadyCallback {

    private static final String TAG = "BookingActivity";
    private static float DEFFAULT_ZOOM = 15f;

    //widgets
    @BindView(R.id.picker_pickUpContainer)
    LinearLayout setPickUpContainer;
    @BindView(R.id.picker_destinationContainer)
    LinearLayout setDestinationContainer;

    //Button
    @BindView(R.id.btn_next)
    Button butonNext;

    //textview
    @BindView(R.id.booking_pickUpText)
    TextView pickUpText;
    @BindView(R.id.booking_destintationText)
    TextView destinationText;
    @BindView(R.id.tv_tripPlan)
    TextView tripPlantText;


    //vars
    private GoogleMap mMap;
    private GeoApiContext mGeoAPiContext = null;
    private FusedLocationProviderClient mFusedLocation;
    private boolean isMapReady = false;
    private LatLng pickUpLatLang;
    private LatLng destinationLatLang;
    private Marker pickUpMarker;
    private Marker destinationMarker;
    private Polyline directionLine;
    private double timeDistance = 0;
    private double jarak;
    private double harga;

    private BookingViewModel viewModel;
    private CheckOutModel checkOutModel;

    private okhttp3.Callback updateRouteCallback = new okhttp3.Callback() {

        @Override
        public void onFailure(okhttp3.Call call, IOException e) {

        }

        @Override
        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
            Log.d(TAG, "onResponse: response route");
            if (response.isSuccessful()) {
                final String json = response.body().string();
                final long distance = MapDirectionAPI.getDistance(BookingActivity.this, json);
                final long time = MapDirectionAPI.getTimeDistance(BookingActivity.this, json);
                if (distance >= 0) {
                    BookingActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateLineDestination(json);
                            updateDistance(distance);
                            timeDistance = time / 60;
                        }
                    });
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ButterKnife.bind(this);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(BookingViewModel.class);

        checkOutModel = new CheckOutModel();

        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        //maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);

        if (mGeoAPiContext == null) {
            Log.d(TAG, "onCreate: init geo api");
            mGeoAPiContext = new GeoApiContext.Builder()
                    .apiKey(getString(R.string.google_maps_key))
                    .build();
        }

        setPickUpContainer.setVisibility(View.GONE);
        setDestinationContainer.setVisibility(View.GONE);


    }

    @OnClick(R.id.container_pickup)
    public void setSetPickUpContainer() {
        setPickUpContainer.setVisibility(View.VISIBLE);
        showdestintaionCOntainer();
    }

    @OnClick(R.id.container_destination)
    public void setSetDestinationContainer() {
        setDestinationContainer.setVisibility(View.VISIBLE);
        showPickUpCOntainer();
    }

    private void showPickUpCOntainer() {
        if (setPickUpContainer.isShown()) {
            setPickUpContainer.setVisibility(View.GONE);
        }
    }

    private void showdestintaionCOntainer() {
        if (setDestinationContainer.isShown()) {
            setDestinationContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng surabya = new LatLng(-7.2755979, 112.572597);
        mMap = googleMap;
        isMapReady = true;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(surabya, 10));
        getLastKnowLocation();
    }

    private void getLastKnowLocation() {
        Log.d(TAG, "getLastKnowLocation: called get last location");
        mFusedLocation.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                Location location = task.getResult();
                pickUpLatLang = new LatLng(location.getLatitude(), location.getLongitude());
                updateMyLocation(pickUpLatLang);
                fillAddress(pickUpText, pickUpLatLang);
//                checkOutModel.setPickupAdress(fillAddress(pickUpLatLang));
            }
        });
    }

    private void updateMyLocation(LatLng location) {
        Log.d(TAG, "updateMyLocation: update location");
        mMap.setMyLocationEnabled(true);
        if (location != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 18f);
            mMap.animateCamera(cameraUpdate);
        }
    }

    @OnClick(R.id.picker_pickUpButton)
    public void onPickUpClick() {
        Log.d(TAG, "onPickUpClick: pick up area");
        if (pickUpMarker != null) pickUpMarker.remove();
        LatLng centerPos = mMap.getCameraPosition().target;
        pickUpMarker = mMap.addMarker(new MarkerOptions()
                .position(centerPos)
                .title("Pick Up")
                .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));
        pickUpLatLang = centerPos;
        fillAddress(pickUpText, pickUpLatLang);
//        checkOutModel.setPickupAdress(fillAddress(pickUpLatLang));
        requestRoute();
        setPickUpContainer.setVisibility(View.GONE);
    }

    @OnClick(R.id.picker_destinationButton)
    public void onDestinationClick() {
        Log.d(TAG, "onDestinationClick: pick destination");
        if (destinationMarker != null) destinationMarker.remove();
        LatLng centerPos = mMap.getCameraPosition().target;
        destinationMarker = mMap.addMarker(new MarkerOptions()
                .position(centerPos)
                .title("Pick Up")
                .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_destination)));
        destinationLatLang = centerPos;

        if (pickUpLatLang != null) {
            if (pickUpMarker != null)
                pickUpMarker.remove();
            pickUpMarker = mMap.addMarker(new MarkerOptions()
                    .position(centerPos)
                    .title("Pick Up")
                    .position(pickUpLatLang)
                    .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));
        }
//        checkOutModel.setDestintaionAddress(fillAddress(destinationLatLang));
        fillAddress(destinationText, destinationLatLang);
        requestRoute();
        setDestinationContainer.setVisibility(View.GONE);
    }

    private void requestRoute() {
        Log.d(TAG, "requestRoute: request router");
        if (pickUpLatLang != null && destinationLatLang != null) {

//            MapDirectionAPI.getDirection(pickUpLatLang, destinationLatLang).enqueue(updateRouteCallback);
            calculateDirection(pickUpLatLang, destinationLatLang);
            CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(destinationLatLang, 15f);
            mMap.animateCamera(camera);
        }
    }

    private void fillAddress(TextView editText, LatLng latLng) {
        viewModel.getAdress(latLng).observe(this, address -> {
            editText.setText(address.getAddressLine(0));
            Log.d(TAG, "onChanged: ");
        });
    }

    private void calculateDirection(LatLng pickUpLatLang, LatLng destinationLatLang) {
        if (pickUpLatLang != null && destinationLatLang != null) {
            Log.d(TAG, "calculateDirection: calculate directions");
            DirectionsApiRequest direction = new DirectionsApiRequest(mGeoAPiContext);
            direction.alternatives(true);
            direction.mode(TravelMode.DRIVING);
            direction.origin(new com.google.maps.model.LatLng(pickUpLatLang.latitude, pickUpLatLang.longitude));
            direction.destination(new com.google.maps.model.LatLng(destinationLatLang.latitude, destinationLatLang.latitude));
            direction.setCallback(new PendingResult.Callback<DirectionsResult>() {
                @Override
                public void onResult(DirectionsResult result) {
                    Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
                    Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
                    Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
                    Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance.inMeters);
                    Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
                }

                @Override
                public void onFailure(Throwable e) {
                    Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage());
                }
            });


        }
    }

    private void updateLineDestination(String json) {
        Log.d(TAG, "updateLineDestination: called" + json);
        Directions directions = new Directions(this);
        try {
            List<Route> routes = directions.parse(json);

            if (directionLine != null) directionLine.remove();
            if (routes.size() > 0) {

                directionLine = mMap.addPolyline((new PolylineOptions())
                        .addAll(routes.get(0).getOverviewPolyLine())
                        .color(ContextCompat.getColor(this, R.color.greenDarkerMain))
                        .width(17));


//                LatLngBounds camera = new LatLngBounds(routes.get(0).getBounds().getSouthWest(), routes.get(0).getBounds().getNorthEast());
//                Log.d(TAG, "updateLineDestination: " + routes.get(0).getOverviewPolyLine());
//                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(camera.getCenter(), 10);
//                mMap.animateCamera(update);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDistance(long distance) {
        tripPlantText.setVisibility(View.VISIBLE);
        butonNext.setVisibility(View.VISIBLE);

        float km = ((float) distance) / Constant.RANGE_VALUE;
        this.jarak = km;


        String format = String.format(Locale.US, "Distance %.2f " + Constant.UNIT_OF_DISTANCE, km);
        // distanceText.setText(format);
        checkOutModel.setDistance(format);
        Log.d(TAG, "updateDistance: " + format);

        long biaya = 5000;
        long biayaMinimum = 8000;

        double biayaTotal = (double) (biaya * km);


        if (biayaTotal % 1 != 0)
            biayaTotal = (1 - (biayaTotal % 1)) + biayaTotal;

        this.harga = biayaTotal;
        if (biayaTotal < biayaMinimum) {
            this.harga = biayaMinimum;
            biayaTotal = biayaMinimum;
        }

//        if (mPayButton.isChecked()) {
//            Log.e("MPAY", "total :" + biayaTotal + " kali " + designedFitur.getBiayaAkhir());
//            biayaTotal *= designedFitur.getBiayaAkhir();
//        }

        String formattedTotal = NumberFormat.getNumberInstance(Locale.US).format(biayaTotal);
        String noCent = String.format(Locale.US, Constant.MONEY + " %s.00", formattedTotal);
//        priceText.setText(noCent);
        checkOutModel.setCost(noCent);
        Log.d(TAG, "updateDistance: " + noCent);

       /* String priceCent = String.format(Locale.US, General.MONEY +" %.2f", biayaTotal);
       priceText.setText(noCent);

        if (General.Cent) {
            priceText.setText(priceCent);
        } else {
            priceText.setText(noCent);
        } */


//        if (saldoMpay < (harga * designedFitur.getBiayaAkhir())) {
//            mPayButton.setEnabled(false);
//            cashButton.toggle();
//        } else {
//            mPayButton.setEnabled(true);
//        }
    }

    @OnClick(R.id.btn_next)
    public void checkOutProcces() {
        checkOutModel.setTimeDistance(String.valueOf(timeDistance));
        viewModel.setCheckOut(checkOutModel);
        CheckOutButtomSheetDialog dialog = new CheckOutButtomSheetDialog(checkOutModel);
        dialog.show(getSupportFragmentManager(), dialog.getTag());
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!mPermissionService) {
            requestPermissions();
        }
    }

}



