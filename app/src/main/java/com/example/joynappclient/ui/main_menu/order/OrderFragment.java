package com.example.joynappclient.ui.main_menu.order;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.main_menu.order.history.HistoryActivity;
import com.example.joynappclient.ui.main_menu.order.inprogress.InProgressAdapter;
import com.example.joynappclient.utils.DummyItem;
import com.example.joynappclient.utils.MoveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    //widget
    @BindView(R.id.recycleview_order)
    RecyclerView order;
    @BindView(R.id.img_history)
    LinearLayout iconHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iconHistory.setOnClickListener(v -> {
            MoveActivity.MoveAct(getContext(), HistoryActivity.class);
        });

        order.setHasFixedSize(true);
        InProgressAdapter adapter = new InProgressAdapter();
        adapter.setProgress(getContext(), DummyItem.getInProgress());
        order.setAdapter(adapter);


    }
}
