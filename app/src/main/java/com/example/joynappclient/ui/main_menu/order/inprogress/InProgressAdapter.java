package com.example.joynappclient.ui.main_menu.order.inprogress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.data.dummy.InProgressModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InProgressAdapter extends RecyclerView.Adapter<InProgressAdapter.Vh> {
    List<InProgressModel> item;
    private Context context;

    public void setProgress(Context context, List<InProgressModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_layout_inprogress, parent, false);

        return new Vh(v);
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
        @BindView(R.id.tv_dateOrder)
        TextView date;
        @BindView(R.id.tv_addressOrder)
        TextView address;
        @BindView(R.id.tv_timeOrder)
        TextView time;

        public Vh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bindItem(InProgressModel model) {
            date.setText(model.getDateOrder());
            address.setText(model.getAddressOrder());
            time.setText(model.getTimeOrder());
        }
    }
}
