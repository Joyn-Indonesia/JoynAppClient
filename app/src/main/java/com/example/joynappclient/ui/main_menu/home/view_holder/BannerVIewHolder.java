package com.example.joynappclient.ui.main_menu.home.view_holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.HomeContentDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerVIewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_title_banner)
    TextView titleBanner;
    @BindView(R.id.tv_content_banner)
    TextView contentBanner;

    public BannerVIewHolder(@NonNull View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bindItem(List<HomeContentDataModel> model) {
        if (!model.isEmpty()) {
            titleBanner.setText(model.get(0).getTitle());
            contentBanner.setText(model.get(0).getContent());
        }

    }
}
