package com.example.joynappclient.ui.main_menu.order;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.joynappclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment getInstance() {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

}
