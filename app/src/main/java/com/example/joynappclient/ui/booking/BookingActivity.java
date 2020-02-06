package com.example.joynappclient.ui.booking;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.remote.model.DriverModel;
import com.example.joynappclient.ui.booking.address.DestinationAddressBottomSheet;
import com.example.joynappclient.ui.booking.address.PickUpAddressBottomSheet;
import com.example.joynappclient.ui.booking.checkout.CheckOutButtomSheet;
import com.example.joynappclient.ui.booking.checkout.CheckOutModel;
import com.example.joynappclient.ui.booking.dialog.AddNote;
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
    private static final String PICKUP = "Pick Up";
    private static final String DESTINTAION = "Destination";
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
    @BindView(R.id.btn_add_note_pickup)
    TextView noteCostumer;
    @BindView(R.id.booking_destintationText)
    TextView destinationText;
    @BindView(R.id.tv_tripPlan)
    TextView tripPlantText;
    @BindColor(R.color.greenDarkerMain)
    int darkGreen;

    //sheetDialog
    private PickUpAddressBottomSheet pickUpAddressBottomSheet;
    private DestinationAddressBottomSheet destintaionAddressBottomSheet;

    //vars
    private Context context;
    private GoogleMap mMap;
    private GeoApiContext mGeoAPiContext = null;
    private FusedLocationProviderClient mFusedLocation;
    private boolean isMapReady = false;
    private LatLng pickUpLatLang;
    private LatLng destinationLatLang;
    private Marker pickUpMarker;
    private Marker destinationMarker;
    private List<DriverModel> driverAvaible;
    private List<Marker> driverMarkers;
    private Polyline directionLine;
    private String timeDistance;
    private double harga;

    //viewModel
    private BookingViewModel viewModel;
    private CheckOutModel checkOutModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);
        context = this;
        initViewModel();

        //maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);

        mGeoAPiContext = new GeoApiContext.Builder()
                .apiKey(getString(R.string.google_maps_key))
                .build();

        setPickUpContainer.setVisibility(View.GONE);
        setDestinationContainer.setVisibility(View.GONE);

        checkOutModel = new CheckOutModel();
        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);

        // viewModel.getCity("Surabaya");
    }

    private void initViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(BookingViewModel.class);

        //pick up
        viewModel.getResponsePickUp().observe(this, status -> {
            switch (status.status) {
                case SEARCH:
                    Place place = status.body;
                    Log.d(TAG, "onChanged: pick up" + place);
                    pickUpText.setText(place.getAddress());
                    pickUpLatLang = place.getLatLng();
                    moveCameraupdate(pickUpLatLang);
                    checkOutModel.setPickupAdress(place.getAddress());
                    createMarkerDirection(PICKUP, pickUpLatLang);
                    break;

                case PICKS:
                    Log.d(TAG, "onChanged: picks up");
                    setPickUpContainer.setVisibility(View.VISIBLE);
                    pickUpAddressBottomSheet.dismiss();
                    break;
            }
        });

        //note
        viewModel.getCheckOut().observe(this, model -> {
            if (model.getNote() != null) {
                noteCostumer.setText(model.getNote());
            } else {
                noteCostumer.setText(getString(R.string.add_note));
            }
        });

        //destintaion
        viewModel.getResponseDestination().observe(this, status -> {
            switch (status.status) {
                case SEARCH:
                    Place place = status.body;
                    Log.d(TAG, "onChanged: destination " + place);
                    destinationText.setText(place.getAddress());
                    destinationLatLang = place.getLatLng();
                    checkOutModel.setDestintaionAddress(place.getAddress());
                    createMarkerDirection(DESTINTAION, destinationLatLang);
                    break;

                case PICKS:
                    Log.d(TAG, "onChanged: destintaion");
                    setDestinationContainer.setVisibility(View.VISIBLE);
                    destintaionAddressBottomSheet.dismiss();
                    break;
            }
        });
    }

    @OnClick(R.id.btn_search_pickup)
    public void setSetPickUpContainer() {
        Log.d(TAG, "setSetPickUpContainer: ");
        pickUpAddressBottomSheet = new PickUpAddressBottomSheet();
        pickUpAddressBottomSheet.show(getSupportFragmentManager(), pickUpAddressBottomSheet.getTag());
    }

    @OnClick(R.id.btn_search_desti)
    public void setSetDestinationContainer() {
        Log.d(TAG, "setSetDestinationContainer: ");
        destintaionAddressBottomSheet = new DestinationAddressBottomSheet();
        destintaionAddressBottomSheet.show(getSupportFragmentManager(), destintaionAddressBottomSheet.getTag());
    }

    @OnClick(R.id.btn_add_note_pickup)
    public void showAddNote() {
        FragmentManager fm = getSupportFragmentManager();
        AddNote dialog = AddNote.getInstance(AddNote.PICKUP);
        dialog.show(fm, dialog.getTag());

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: ");
        LatLng surabya = new LatLng(-7.2755979, 112.572597);
        mMap = googleMap;
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
        Log.d(TAG, "updateMyLocation: updateUserLogin location");
        mMap.setMyLocationEnabled(true);
        if (location != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15f);
            mMap.animateCamera(cameraUpdate);
            fetchNearDriver();
        }
    }

    private void fetchNearDriver() {
        Log.d(TAG, "fetchNearDriver: get near driver");
        if (pickUpLatLang != null) {
            FirebaseFirestore mDb = FirebaseFirestore.getInstance();
            CollectionReference reffUser = mDb.collection(getString(R.string.collection_drivers));
            driverAvaible = new ArrayList<>();
            driverMarkers = new ArrayList<>();
            reffUser.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "fetchNearDriver: succes");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        driverAvaible.add(new DriverModel(document.getGeoPoint("Location")));
                    }
                    createDriverMarker(driverAvaible);
                } else {
                    Log.d(TAG, "get driver failed: failed");
                }
            });
        }
    }

    private void createDriverMarker(List<DriverModel> drivers) {
        Log.d(TAG, "createDriverMarker: ");
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
        setPickUpContainer.setVisibility(View.GONE);
        LatLng centerPos = mMap.getCameraPosition().target;
        createMarkerDirection(PICKUP, centerPos);
        fillAddress(pickUpText, centerPos, 1);
    }

    @OnClick(R.id.picker_destinationButton)
    public void onDestinationClick() {
        Log.d(TAG, "onDestinationClick: pick destination");
        LatLng centerPos = mMap.getCameraPosition().target;
        createMarkerDirection(DESTINTAION, centerPos);
        fillAddress(destinationText, centerPos, 2);
    }

    public void createMarkerDirection(String params, LatLng position) {
        if (params.equals("Pick Up")) {
            if (pickUpMarker != null) pickUpMarker.remove();
            setDestinationContainer.setVisibility(View.GONE);
            pickUpMarker = mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(params)
                    .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));
            this.pickUpLatLang = position;
        } else {
            if (destinationMarker != null) destinationMarker.remove();

            if (pickUpMarker == null) {
                if (pickUpLatLang != null) {
                    pickUpMarker = mMap.addMarker(new MarkerOptions()
                            .position(pickUpLatLang)
                            .title(params)
                            .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pickups)));
                }
            }

            setDestinationContainer.setVisibility(View.GONE);
            destinationMarker = mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(params)
                    .icon(VectorDescriptor.bitmapDescriptorFromVector(this, R.drawable.ic_pin_destination)));
            this.destinationLatLang = position;
            setDestinationContainer.setVisibility(View.GONE);
        }
        requestRoute();
    }

    private void moveCameraupdate(LatLng latLng) {
        Log.d(TAG, "moveCameraupdate:");
        CameraUpdate updateFactory = CameraUpdateFactory.newLatLngZoom(latLng, 15f);
        mMap.animateCamera(updateFactory);
    }

    private void requestRoute() {
        Log.d(TAG, "requestRoute: request router");
        if (pickUpLatLang != null && destinationLatLang != null) {
            calculateDirection(pickUpLatLang, destinationLatLang);
        }
    }

    private void fillAddress(TextView textView, LatLng latLng, int param) {
        Log.d(TAG, "fillAddress: ");
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
            Log.d(TAG, "calculateDirection: " + pickUpLatLang + ", " + destinationLatLang);
            Log.d(TAG, "calculateDirection: calculate directions");
            DirectionsApiRequest direction = new DirectionsApiRequest(mGeoAPiContext);

            direction.alternatives(false)
                    .mode(TravelMode.DRIVING).language("id")
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
            Log.d(TAG, "updateLineDestination: ");
            new Handler(getMainLooper()).post(() -> {

                List<LatLng> newDecodedPath = new ArrayList<>();
                for (com.google.maps.model.LatLng latLng : result.routes[0].overviewPolyline.decodePath()) {
                    newDecodedPath.add(new LatLng(latLng.lat, latLng.lng));
                }

                if (directionLine != null) directionLine.remove();
                directionLine = mMap.addPolyline((new PolylineOptions())
                        .addAll(newDecodedPath)
                        .color(darkGreen)
                        .width(17));

                LatLng southwest = new LatLng(result.routes[0].bounds.southwest.lat, result.routes[0].bounds.southwest.lng);
                LatLng northeast = new LatLng(result.routes[0].bounds.northeast.lat, result.routes[0].bounds.northeast.lng);

                LatLngBounds bounds = new LatLngBounds(southwest, northeast);
                CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, 600, 600, 100);
                mMap.animateCamera(update);
                updateDistance(result.routes[0].legs[0].distance);
                timeDistance = result.routes[0].legs[0].duration.toString();
            });
        }
    }

    private void updateDistance(Distance distance) {
        Log.d(TAG, "updateDistance: ");
        tripPlantText.setVisibility(View.VISIBLE);
        butonNext.setVisibility(View.VISIBLE);

        checkOutModel.setDistance(distance.toString());
        float km = ((float) distance.inMeters) / Constant.RANGE_VALUE;

        long biaya = 2;
        long biayaMinimum = 8;

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
        String noCent = String.format(Locale.US, Constant.MONEY + " %s.000,-", formattedTotal);
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
        Log.d(TAG, "checkOutProcces: click");
        checkOutModel.setTimeDistance(timeDistance);
        viewModel.setCheckOutModel(checkOutModel);
        CheckOutButtomSheet dialog = new CheckOutButtomSheet(context);
        dialog.show(getSupportFragmentManager(), dialog.getTag());
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        if (!mPermissionService) {
            requestPermissions();
        }
    }

}



