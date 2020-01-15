package com.example.joynappclient.ui.j_food.food;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.utils.DummyItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JFoodActivity extends AppCompatActivity {

    //widget
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview_jfood)
    RecyclerView rvContent;
    @BindView(R.id.tv_title_toolbar)
    TextView titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jfood);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            titlebar.setText("Jl. Dr. Ir. H. Soekarno");
            titlebar.setTextColor(getResources().getColor(R.color.black80));
        }

        AdapterContentTitle title = new AdapterContentTitle();
        title.setItem(this, DummyItem.getAllData());

        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setHasFixedSize(true);
        rvContent.setAdapter(title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_j_food, menu);

        return true;
    }
}
