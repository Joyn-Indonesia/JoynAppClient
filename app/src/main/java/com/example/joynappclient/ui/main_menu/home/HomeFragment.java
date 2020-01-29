package com.example.joynappclient.ui.main_menu.home;


import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    //Widget
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview_mainContent)
    RecyclerView rvSnack;
    @BindView(R.id.recycleview_button)
    RecyclerView rvButton;
    //var
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

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
        rvSnack.setAdapter(snack);
        initButton();
        //initToolbar();

    }

    private void initButton() {

        List<String> title = new ArrayList<>();
        title.add("All");
        title.add("Food");
        title.add("Promo");
        title.add("Payment");
        title.add("LifeStyles");
        title.add("HangOuts");
        title.add("Party");

        ButtonAdapter adapter = new ButtonAdapter();
        adapter.setItem(context, title);
        rvButton.setHasFixedSize(true);
        rvButton.setAdapter(adapter);

    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(true);
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
