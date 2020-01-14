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
import com.example.joynappclient.ui.j_food.JFoodActivity;
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


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    //Widget
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview_snack)
    RecyclerView rvSnack;
    @BindView(R.id.recycleview_recomendResto)
    RecyclerView rvResto;
    @BindView(R.id.recycleview_nearbyRestaurant)
    RecyclerView rvNearby;
    @BindView(R.id.recycleview_bestPick)
    RecyclerView rvBestPick;
    @BindView(R.id.recycleview_dishesLiked)
    RecyclerView rvDishesLiked;
    @BindView(R.id.recycleview_breakfast)
    RecyclerView rvBreakfast;
    @BindView(R.id.recycleview_highlightedDishes)
    RecyclerView rvHighlightDishes;
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
        rvResto.setHasFixedSize(true);
        rvNearby.setHasFixedSize(true);
        rvBestPick.setHasFixedSize(true);
        rvDishesLiked.setHasFixedSize(true);
        rvBreakfast.setHasFixedSize(true);
        rvHighlightDishes.setHasFixedSize(true);

        HomeFragmentAdapter snack = new HomeFragmentAdapter();
        HomeFragmentAdapter resto = new HomeFragmentAdapter();
        HomeFragmentAdapter nearby = new HomeFragmentAdapter();
        HomeFragmentAdapter bestpick = new HomeFragmentAdapter();
        HomeFragmentAdapter liked = new HomeFragmentAdapter();
        HomeFragmentAdapter breakfast = new HomeFragmentAdapter();
        HomeFragmentAdapter highligh = new HomeFragmentAdapter();


        snack.setItem(context, DummyItem.getFoodSnack());
        resto.setItem(context, DummyItem.getRecomendResto());
        nearby.setItem(context, DummyItem.getNearbyReto());
        bestpick.setItem(context, DummyItem.getBestPick());
        liked.setItem(context, DummyItem.getDhisesLike());
        breakfast.setItem(context, DummyItem.getBreakfast());
        highligh.setItem(context, DummyItem.getHighligtedDishes());

        rvSnack.setAdapter(snack);
        rvResto.setAdapter(resto);
        rvNearby.setAdapter(nearby);
        rvBestPick.setAdapter(bestpick);
        rvDishesLiked.setAdapter(liked);
        rvBreakfast.setAdapter(breakfast);
        rvHighlightDishes.setAdapter(highligh);

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
