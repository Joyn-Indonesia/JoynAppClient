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
import com.example.joynappclient.data.JFoodContentModelDummy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContentItem extends RecyclerView.Adapter<AdapterContentItem.VH> {
    private List<JFoodContentModelDummy> item = new ArrayList<>();
    private Context context;

    public AdapterContentItem() {
    }

    public void setItem(Context contex, List<JFoodContentModelDummy> item) {
        this.item = item;
        this.context = contex;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout_content_item, parent, false);

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

        void bindItem(JFoodContentModelDummy model) {

            Glide.with(context)
                    .load(model.getImgContent())
                    .into(imgContent);

            titleContent.setText(model.getTitleContentl());

        }
    }
}
