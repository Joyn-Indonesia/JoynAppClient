package com.example.joynappclient.ui.booking;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.remote.model.DriverModel;
import com.example.joynappclient.ui.booking.checkout.CheckOutButtomSheetDialog;
import com.example.joynappclient.ui.booking.checkout.CheckOutModel;
import com.example.joynappclient.ui.booking.pickup.AddressPickUpBottomSheet;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.model.Place;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.TravelMode;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
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
    @BindColor(R.color.greenDarkerMain)
    int darkGreen;
    //sheetDialog
    AddressPickUpBottomSheet addressBottomSheet;
    //vars
    private GoogleMap mMap;
    private GeoApiContext mGeoAPiContext = null;
    private FusedLocationProviderClient mFusedLocation;
    private boolean isMapReady = false;
    private LatLng pickUpLatLang;
    private LatLng destinationLatLang;
    private Marker pickUpMarker;
    private Marker destinationMarker;
    private String addressLine;
    private List<DriverModel> driverAvaible;
    private List<Marker> driverMarkers;
    private Polyline directionLine;
    private String timeDistance;
    private double harga;
    private BookingViewModel viewModel;
    private CheckOutModel checkOutModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        setPickUpContainer.setVisibility(View.GONE);
        setDestinationContainer.setVisibility(View.GONE);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(BookingViewModel.class);
        checkOutModel = new CheckOutModel();
        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        driverMarkers = new ArrayList<>();

        //maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);

        if (mGeoAPiContext == null) {
            Log.d(TAG, "onCreate: init geo api");
            mGeoAPiContext = new GeoApiContext.Builder()
                    .apiKey(getString(R.string.google_maps_key))
                    .build();
        }

        // viewModel.getCity("Surabaya");

        initViewModel();

    }

    private void initViewModel() {

        viewModel.getResponsePickUp().observe(this, status -> {
            switch (status.status) {
                case SEARCH:
                    Place place = status.body;
                    Log.d(TAG, "onChanged: " + place);
                    pickUpText.setText(place.getAddress());
                    pickUpLatLang = place.getLatLng();
                    moveCameraupdate(pickUpLatLang);
                    createPickUpMarker(pickUpLatLang);
                    showPickUpCOntainer();
                    break;

                case PICKS:
                    Log.d(TAG, "onChanged: picks");
                    setPickUpContainer.setVisibility(View.VISIBLE);
                    showdestintaionCOntainer();
                    addressBottomSheet.dismiss();
                    break;
            }
        });

        viewModel.getResponseDestination().observe(this, status -> {
            switch (status.status) {
                case SEARCH:
                    Place place = status.body;
                    Log.d(TAG, "onChanged: " + place);
                    destinationText.setText(place.getAddress());
                    destinationLatLang = place.getLatLng();
                    createPickUpMarker(destinationLatLang);
                    break;

                case PICKS:
                    Log.d(TAG, "onChanged: picks");
                    setDestinationContainer.setVisibility(View.VISIBLE);
                    showPickUpCOntainer();
                    addressBottomSheet.dismiss();
                    break;
            }
        });


//        viewModel.getPlaceSearchPickup().observe(this, place -> {
//            pickUpText.setText(place.getAddress());
//            pickUpLatLang = place.getLatLng();
//            moveCameraupdate(pickUpLatLang);
//            createPickUpMarker(pickUpLatLang);
//        });

    }

    @OnClick(R.id.container_pickup)
    public void setSetPickUpContainer() {
        addressBottomSheet = new AddressPickUpBottomSheet();
        addressBottomSheet.show(getSupportFragmentManager(), addressBottomSheet.getTag());

//        setPickUpContainer.setVisibility(View.VISIBLE);
//        showdestintaionCOntainer();
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
                viewModel.setPickup(pickUpLatLang);
                updateMyLocation(pickUpLatLang);
                fillAddress(pickUpText, pickUpLatLang, 1);
            }
        });
    }

    private void updateMyLocation(LatLng location) {
        Log.d(TAG, "updateMyLocation: update location");
        mMap.setMyLocationEnabled(true);
        if (location != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15f);
            mMap.animateCamera(cameraUpdate);
            fetchNearDriver();
        }
    }

    private void fetchNearDriver() {
        if (pickUpLatLang != null) {
            FirebaseFirestore mDb = FirebaseFirestore.getInstance();
            CollectionReference reffUser = mDb.collection(getString(R.string.collection_drivers));
            driverAvaible = new ArrayList<>();
            reffUser.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: get user complete" + task.getResult().size());
                    Log.d(TAG, "fetchNearDriver: " + task.getResult().getDocuments().get(0).get("Location"));

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, "fetchNearDriver: " + document.getData().get("Location"));
                        driverAvaible.add(new DriverModel(document.getGeoPoint("Location")));
                    }
                    Log.d(TAG, "fetchNearDriver: " + driverAvaible.size());
                    createMarker(driverAvaible);
                } else {
                    Log.d(TAG, "getUserDetail: failed");
                }
            });
        }
    }

    private void createMarker(List<DriverModel> drivers) {
        if (!drivers.isEmpty()) {
            for (Marker m : driverMarkers) {
                m.remove();
            }
            driverMarkers.clear();

            for (DriverModel driver : drivers) {
                LatLng curentDriverPos = new LatLng(driver.getGeoPoint().getLatitude(), driver.getGeoPoint().getLongitude());
                driverMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(curentDriverPos)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_ride_position))));
            }
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
        this.pickUpLatLang = centerPos;
        fillAddress(pickUpText, centerPos, 1);
        requestRoute();
    }

    public void createPickUpMarker(LatLng pickUpLatLang) {
        Log.d(TAG, "createPickUpMarker: ");
        if (pickUpMarker != null) pickUpMarker.remove();
        createMarkerDirection("Pick Up", pickUpLatLang);
//        pickUpMarker = mMap.addMarker(new MarkerOptions()
//                .position(pickUpLatLang)
//                .title("Pick Up")
//                .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));
//        this.pickUpLatLang = pickUpLatLang;

    }

    private void createMarkerDirection(String param, LatLng position) {
        if (param.equals("Pick Up")) {
            pickUpMarker = mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(param)
                    .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));
            this.pickUpLatLang = position;
        } else {
            destinationMarker = mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title("Destination")
                    .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_destination)));
            this.destinationLatLang = position;
            setDestinationContainer.setVisibility(View.GONE);
        }

        requestRoute();
    }

    @OnClick(R.id.picker_destinationButton)
    public void onDestinationClick() {
        Log.d(TAG, "onDestinationClick: pick destination");
        if (destinationMarker != null) destinationMarker.remove();
        LatLng centerPos = mMap.getCameraPosition().target;
        destinationMarker = mMap.addMarker(new MarkerOptions()
                .position(centerPos)
                .title("Destination")
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
        fillAddress(destinationText, destinationLatLang, 2);
        requestRoute();
        setDestinationContainer.setVisibility(View.GONE);
    }

    private void moveCameraupdate(LatLng latLng) {
        CameraUpdate updateFactory = CameraUpdateFactory.newLatLngZoom(latLng, 15f);
        mMap.animateCamera(updateFactory);

    }

    private void requestRoute() {
        Log.d(TAG, "requestRoute: request router");
        if (pickUpLatLang != null && destinationLatLang != null) {
            calculateDirection(pickUpLatLang, destinationLatLang);
            moveCameraupdate(destinationLatLang);
        }
    }

    private void fillAddress(TextView textView, LatLng latLng, int param) {
        String addres = viewModel.getAdress(latLng);
        textView.setText(addres);
        if (param == 1) {
            checkOutModel.setPickupAdress(addres);
        } else {
            checkOutModel.setDestintaionAddress(addres);
        }
    }

    private void calculateDirection(LatLng pickUpLatLang, LatLng destinationLatLang) {
        if (pickUpLatLang != null && destinationLatLang != null) {
            Log.d(TAG, "calculateDirection: calculate directions");

            DirectionsApiRequest direction = new DirectionsApiRequest(mGeoAPiContext);
            direction.alternatives(true)
                    .mode(TravelMode.DRIVING)
                    .language("id")
                    .origin(new com.google.maps.model.LatLng(pickUpLatLang.latitude, pickUpLatLang.longitude))
                    .destination(new com.google.maps.model.LatLng(destinationLatLang.latitude, destinationLatLang.longitude))
                    .setCallback(new PendingResult.Callback<DirectionsResult>() {
                        @Override
                        public void onResult(DirectionsResult result) {
                            updateLineDestination(result);
                            Log.d(TAG, "onResult: result");
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage());
                        }
                    });
        }
    }

    private void updateLineDestination(DirectionsResult result) {
        if (result != null) {
            List<LatLng> newDecodedPath = new ArrayList<>();
            for (com.google.maps.model.LatLng latLng : result.routes[0].overviewPolyline.decodePath()) {
                newDecodedPath.add(new LatLng(latLng.lat, latLng.lng));
            }
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (directionLine != null) directionLine.remove();
                    directionLine = mMap.addPolyline((new PolylineOptions())
                            .addAll(newDecodedPath)
                            .color(darkGreen)
                            .width(17));

                    LatLng southwest = new LatLng(result.routes[0].bounds.southwest.lat, result.routes[0].bounds.southwest.lng);
                    LatLng northeast = new LatLng(result.routes[0].bounds.northeast.lat, result.routes[0].bounds.northeast.lng);

                    LatLngBounds bounds = new LatLngBounds(southwest, northeast);
                    CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, 200, 200, 0);
                    mMap.animateCamera(update);
                    updateDistance(result.routes[0].legs[0].distance);
                    timeDistance = result.routes[0].legs[0].duration.toString();
                }
            });
        }
    }

    private void updateDistance(Distance distance) {
        tripPlantText.setVisibility(View.VISIBLE);
        butonNext.setVisibility(View.VISIBLE);

        checkOutModel.setDistance(distance.toString());
        float km = ((float) distance.inMeters) / Constant.RANGE_VALUE;

        long biaya = 3000;
        long biayaMinimum = 10000;

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
        CheckOutButtomSheetDialog dialog = new CheckOutButtomSheetDialog();
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



