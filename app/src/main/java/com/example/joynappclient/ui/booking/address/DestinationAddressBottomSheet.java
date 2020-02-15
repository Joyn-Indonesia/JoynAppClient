package com.example.joynappclient.ui.booking.address;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.ui.booking.adapter.PlacesAutoCompleteAdapter;
import com.example.joynappclient.ui.booking.utils.StatusResponse;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DestinationAddressBottomSheet extends BottomSheetDialogFragment implements PlacesAutoCompleteAdapter.ClickListener {
    private static final String TAG = "PickUpAddressBottomShee";

    @BindView(R.id.extraSpace)
    View extraSpace;

    private BookingViewModel viewModel;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    private RecyclerView recyclerView;
    private BottomSheetBehavior bottomSheetBehavior;


    BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

            if (4 == newState) {
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

    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")) {
                mAutoCompleteAdapter.getFilter().filter(s.toString());
                if (recyclerView.getVisibility() == View.GONE) {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            } else {
                if (recyclerView.getVisibility() == View.VISIBLE) {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    };
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_bottom_search_adress, container, false);
        ButterKnife.bind(this, view);
        extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels));
        return view;
    }

    @BindView(R.id.tv_title_destination)
    TextView destintaionTitle;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        destintaionTitle.setText(getContext().getString(R.string.where_are_you_going));

        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
        viewModel = new ViewModelProvider(getActivity(), factory).get(BookingViewModel.class);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        bottomSheetBehavior.addBottomSheetCallback(callback);

        Places.initialize(getActivity().getApplicationContext(), getString(R.string.google_maps_key));
        PlacesClient placesClient = Places.createClient(getContext());

        recyclerView = view.findViewById(R.id.places_recycler_view);
        ((EditText) view.findViewById(R.id.et_search_places)).addTextChangedListener(filterTextWatcher);

        LatLng southwest = new LatLng(-7.356889, 112.591545);
        LatLng northeast = new LatLng(-7.1915459, 112.846629);
        LatLngBounds latLngBoundsSurabaya = new LatLngBounds(southwest, northeast);

        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(getContext(), placesClient, latLngBoundsSurabaya);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mAutoCompleteAdapter.setClickListener(this);
        recyclerView.setAdapter(mAutoCompleteAdapter);
        mAutoCompleteAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_select_from_map)
    public void selectFromMap() {
        Log.d(TAG, "selectFromMap: ");
        viewModel.setResponsePlacesDestintaion(StatusResponse.PICKS, null);
    }

    @Override
    public void click(Place place) {
        viewModel.setResponsePlacesDestintaion(StatusResponse.SEARCH, place);
        dismiss();
    }

}
