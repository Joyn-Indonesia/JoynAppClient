package com.example.joynappclient.ui.j_food.food;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.JfoodTitleModelDummy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContentTitle extends RecyclerView.Adapter<AdapterContentTitle.VH> {
    private static final String TAG = "AdapterContentTitle";
    private List<JfoodTitleModelDummy> item = new ArrayList<>();
    private Context context;

    public void setItem(Context context, List<JfoodTitleModelDummy> item) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_home_content, parent, false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindItem(item.get(position));
    }

    @Override
    public int getItemCount() {
        if (item == null) {
            Log.d(TAG, "getItemCount: ");
            return 0;
        }
        Log.d(TAG, "getItemCount: 6++");
        return item.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.recycleview_itemContent)
        RecyclerView rvItemContent;
        @BindView(R.id.tv_highlight_content)
        TextView titleContent;

        private AdapterContentItem adapter;

        public VH(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);
            adapter = new AdapterContentItem();
        }

        void bindItem(JfoodTitleModelDummy model) {
            Log.d(TAG, "bindItem: " + model.getTitle());
            rvItemContent.setHasFixedSize(true);
            rvItemContent.setItemAnimator(new DefaultItemAnimator());
            titleContent.setText(model.getTitle());
            adapter.setItem(context, item.get(getAdapterPosition()).getContent());
            rvItemContent.setAdapter(adapter);
        }
    }
}
