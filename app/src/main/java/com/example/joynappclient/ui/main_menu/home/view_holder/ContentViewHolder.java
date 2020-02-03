package com.example.joynappclient.ui.main_menu.home.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.HomeContentDataModel;
import com.example.joynappclient.ui.main_menu.home.adapter.HomeContentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_highlight_content)
    TextView highLight;
    @BindView(R.id.recycleview_itemContent)
    RecyclerView recyclerView;

    public ContentViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void bindItem(Context context, String contentHighLight, List<HomeContentDataModel> item) {
        highLight.setText(contentHighLight);
        HomeContentAdapter adapter = new HomeContentAdapter();
        adapter.setItem(context, item);
        recyclerView.setAdapter(adapter);

    }
}
