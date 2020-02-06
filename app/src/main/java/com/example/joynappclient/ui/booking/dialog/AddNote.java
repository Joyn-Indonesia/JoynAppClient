package com.example.joynappclient.ui.booking.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.ui.booking.BookingViewModel;
import com.example.joynappclient.ui.booking.checkout.CheckOutModel;
import com.example.joynappclient.viewmodel.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNote extends DialogFragment {

    public static final String PARAMS = "param";
    public static final String PICKUP = "picup";
    @BindView(R.id.input_note)
    EditText inputNote;

    public AddNote() {
    }

    public static AddNote getInstance(String params) {
        AddNote dialog = new AddNote();
        Bundle arg = new Bundle();
        arg.putString(PARAMS, params);
        dialog.setArguments(arg);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_Alert);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_dialog_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
        BookingViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(BookingViewModel.class);

        viewModel.getCheckOut().observe(getActivity(), new Observer<CheckOutModel>() {
            @Override
            public void onChanged(CheckOutModel model) {
                inputNote.setText(model.getNote());
            }
        });

        view.findViewById(R.id.btn_save_note).setOnClickListener(v -> {
            if (!TextUtils.isEmpty(inputNote.getText().toString())) {
                viewModel.setNote(inputNote.getText().toString());
            }
            dismiss();
        });


    }

}
