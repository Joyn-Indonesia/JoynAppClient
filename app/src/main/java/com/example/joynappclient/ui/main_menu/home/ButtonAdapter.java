package com.example.joynappclient.ui.main_menu.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.VH> {

    private List<String> item = new ArrayList<>();
    private Context context;
    private int index = 0;

    public void setItem(Context context, List<String> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_button_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.top_pick.setText(item.get(position));
        holder.top_pick.setOnClickListener(v -> {
            index = position;
            notifyDataSetChanged();
        });
        if (index == position) {
            holder.top_pick.setBackgroundColor(context.getResources().getColor(R.color.greenDarkerMain));
            holder.top_pick.setTextColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.top_pick.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            holder.top_pick.setTextColor(context.getResources().getColor(R.color.halfBlack));
        }
    }

    @Override
    public int getItemCount() {
        if (item.isEmpty()) {
            return 0;
        }
        return item.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_top_pick)
        Button top_pick;

        public VH(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}
