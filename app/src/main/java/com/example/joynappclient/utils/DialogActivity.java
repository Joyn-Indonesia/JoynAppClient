package com.example.joynappclient.utils;

import android.app.ProgressDialog;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;


public class DialogActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    public void showProgressDialog(@StringRes int stringRes) {
        String message = getResources().getString(stringRes);
        dialog = ProgressDialog.show(this, "", message, false, false);
    }

    public void showProgressDialog(String message) {
        dialog = ProgressDialog.show(this, "", message, false, false);
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

}