package com.example.joynappclient.ui.main_menu.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.HomeContentTipeModel;
import com.example.joynappclient.ui.main_menu.home.view_holder.BannerVIewHolder;
import com.example.joynappclient.ui.main_menu.home.view_holder.ContentViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeBannerAdapter extends RecyclerView.Adapter {

    private static final String TAG = "HomeBannerAdapter";
    private static int TYPE_BANNER = 1;
    private static int TYPE_CONTENT = 2;

    private List<HomeContentTipeModel> item = new ArrayList<>();
    private Context context;

    public void setBanner(Context context, List<HomeContentTipeModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;

        if (viewType == TYPE_BANNER) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_menu_home_banner, parent, false);

            return new BannerVIewHolder(v);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_menu_home_content, parent, false);

            return new ContentViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_BANNER) {
            ((BannerVIewHolder) holder).bindItem(item.get(position).getContentData());
        } else {
            ((ContentViewHolder) holder).bindItem(context, item.get(position).getHigLight(), item.get(position).getContentData());
        }
    }


    @Override
    public int getItemCount() {
        if (item.isEmpty())
            return 0;
        return item.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (item.get(position).getTipe().equals("banner")) {
            Log.d(TAG, "getItemViewType: banner");
            return TYPE_BANNER;
        } else {
            return TYPE_CONTENT;
        }

    }
}
