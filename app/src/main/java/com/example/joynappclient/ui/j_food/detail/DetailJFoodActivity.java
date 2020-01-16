package com.example.joynappclient.ui.j_food.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.j_food.cart.CartAdapter;
import com.example.joynappclient.ui.main_menu.order.checkout.CheckOutBottomSheet;
import com.example.joynappclient.utils.DummyItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailJFoodActivity extends AppCompatActivity {

    @BindView(R.id.recycleview_cart)
    RecyclerView rvCart;
    @BindView(R.id.recycleview_food)
    RecyclerView food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jfood);

        ButterKnife.bind(this);

        rvCart.setHasFixedSize(true);
        rvCart.setItemAnimator(new DefaultItemAnimator());
        CartAdapter adapter = new CartAdapter();
        adapter.setCart(this, DummyItem.getCart());
        rvCart.setAdapter(adapter);

        food.setHasFixedSize(true);
        food.setItemAnimator(new DefaultItemAnimator());
        FoodAdapter foodAdapter = new FoodAdapter();
        foodAdapter.setFood(this, DummyItem.getFood());
        food.setAdapter(foodAdapter);
    }

    @OnClick(R.id.btn_order)
    public void showBottomSheetDialog() {
        CheckOutBottomSheet bottomSheetFragment = new CheckOutBottomSheet();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}
