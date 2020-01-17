package com.example.joynappclient.ui.main_menu.inbox;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.joynappclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {


    public InboxFragment() {
        // Required empty public constructor
    }

    public static InboxFragment getInstance() {
        return new InboxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

}
