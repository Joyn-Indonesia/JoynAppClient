package com.example.joynappclient.ui.main_menu.home;

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
import com.example.joynappclient.data.JfoodModelDummyl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.VH> {
    private List<JfoodModelDummyl> item = new ArrayList<>();
    private Context context;

    public void setItem(Context context, List<JfoodModelDummyl> item) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout_j_food, parent, false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindItem(item.get(position));
    }

    @Override
    public int getItemCount() {
        if (item == null) {
            return 0;
        }
        return item.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.img_content)
        ImageView imgContent;
        @BindView(R.id.tv_name_content)
        TextView titleContent;

        public VH(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);

        }

        void bindItem(JfoodModelDummyl model) {

            Glide.with(context)
                    .load(model.getImgContent())
                    .into(imgContent);

            titleContent.setText(model.getTitleContentl());

        }
    }
}
