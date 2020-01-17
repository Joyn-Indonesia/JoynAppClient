package com.example.joynappclient.ui.main_menu.order.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.main_menu.order.inprogress.InProgressAdapter;
import com.example.joynappclient.utils.DummyItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

//    widgets
    @BindView(R.id.recycleview_order)
    RecyclerView history;
    @BindView(R.id.img_iconInProgress)
    LinearLayout iconInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ButterKnife.bind(this);

        iconInProgress.setOnClickListener(v ->{
            finish();
        });

        history.setHasFixedSize(true);
        InProgressAdapter adapter = new InProgressAdapter();
        adapter.setProgress(this, DummyItem.getHistory());
        history.setAdapter(adapter);

    }
}
