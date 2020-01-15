package com.example.joynappclient.ui.j_food.cart;

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
import com.example.joynappclient.data.CartModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Vh> {

    private Context context;
    private List<CartModel> item = new ArrayList<>();

    public void setCart(Context context, List<CartModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_layout_cart, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.bindItem(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        @BindView(R.id.img_content)
        ImageView imgContent;
        @BindView(R.id.tv_titleContent)
        TextView title;
        @BindView(R.id.tv_addNotes)
        TextView addNotes;
        @BindView(R.id.tv_priceContent)
        TextView price;

        public Vh(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        void bindItem(CartModel model) {
            Glide.with(context)
                    .load(model.getImgContent())
                    .into(imgContent);
            title.setText(model.getTitle());
            price.setText(model.getPrice());

        }
    }
}
