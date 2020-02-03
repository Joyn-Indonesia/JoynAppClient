package com.example.joynappclient.ui.booking.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.joynappclient.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ExampleButtomSheetDialog extends BottomSheetDialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_bottom_sheet_booking, container, false);

        TextView button1 = v.findViewById(R.id.tv_favaddres);
        TextView button2 = v.findViewById(R.id.tv_hisaddres);

        return v;
    }

}
