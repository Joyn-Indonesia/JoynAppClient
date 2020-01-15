package com.example.joynappclient.ui.main_menu.home;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingActivity;
import com.example.joynappclient.ui.j_food.food.JFoodActivity;
import com.example.joynappclient.utils.DummyItem;
import com.example.joynappclient.utils.MoveActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    //Widget
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview_mainContent)
    RecyclerView rvSnack;

    //var
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();

        rvSnack.setHasFixedSize(true);
        AdapterContentTitle snack = new AdapterContentTitle();
        snack.setItem(context, DummyItem.getAllData());
        Log.d(TAG, "onActivityCreated: " + DummyItem.getAllData().size());
        rvSnack.setAdapter(snack);

        //initToolbar();
    }

    private void initToolbar() {

        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            setHasOptionsMenu(true);
            bar.setTitle("BALANCE Rp. 56.000");
        }
    }

    @OnClick(R.id.menu_JRide)
    void moveRide() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JSend)
    void moveSend() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JCar)
    void moveCar() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JBox)
    void moveBox() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JFood)
    void moveFood() {
        MoveActivity.MoveAct(context, JFoodActivity.class);
    }

    @OnClick(R.id.menu_JShoop)
    void moveShoop() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_JMall)
    void moveMall() {
        MoveActivity.MoveAct(context, BookingActivity.class);
    }

    @OnClick(R.id.menu_more)
    public void showBottomSheetDialog() {
        HomeMenuServiceSheetDialog bottomSheetFragment = new HomeMenuServiceSheetDialog();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

}
