package com.example.joynappclient.ui.j_food.detail;

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
import com.example.joynappclient.data.dummy.FoodModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.VH> {
    private Context context;
    private List<FoodModel> item = new ArrayList<>();

    public void setFood(Context context, List<FoodModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout_food, parent, false);
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
        @BindView(R.id.tv_titleContent)
        TextView title;
        @BindView(R.id.tv_content)
        TextView content;
        @BindView(R.id.tv_priceContent)
        TextView price;

        public VH(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        void bindItem(FoodModel model) {

            Glide.with(context)
                    .load(model.getContentImage())
                    .into(imgContent);

            title.setText(model.getTitleContent());
            content.setText(model.getContent());
            price.setText(model.getPrice());
        }
    }
}
