package com.example.joynappclient.ui.main_menu.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.HomeContentDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeContentAdapter extends RecyclerView.Adapter<HomeContentAdapter.Vh> {
    private List<HomeContentDataModel> item;
    private Context context;

    public void setItem(Context context, List<HomeContentDataModel> item) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_layout_content_item, parent, false);

        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.nameContent.setText(item.get(position).getTitle());
        Glide.with(context)
                .load(item.get(position).getImageContetn())
                .into(holder.imgContent);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_content)
        TextView nameContent;
        @BindView(R.id.img_content)
        ImageView imgContent;

        public Vh(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}
