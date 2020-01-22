package com.example.joynappclient.ui.authentication.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.UserModel;
import com.example.joynappclient.ui.authentication.otp.OtpActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements Validator.ValidationListener {
    private static final String TAG = "SignInActivity";

    //    wigets

    @BindView(R.id.phone_number)
    AutoCompleteTextView editTextFilledExposedDropdown;

    @NotEmpty
    @Length(min = 10, max = 12, trim = true, message = "number is wrong")
    @BindView(R.id.input_phoneNumber)
    EditText inputPhone;
    @BindString(R.string.intent_params)
    String params;
    //    vars
    private FirebaseFirestore mDb;
    private Context context;
    private Validator validator;

    @OnClick(R.id.tv_signUp)
    public void signUp() {
        finish();
    }

    @OnClick({R.id.btn_whatsapp, R.id.btn_sms, R.id.btn_call})
    public void onMethodClick() {
        validator.validate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mDb = FirebaseFirestore.getInstance();

        context = this;
        validator = new Validator(this);
        validator.setValidationListener(this);
        setAdapterCountryCode();
    }

    private void setAdapterCountryCode() {
        String[] COUNTRIES = new String[]{"+62", "+63", "+64"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, COUNTRIES
        );
        editTextFilledExposedDropdown.setText(COUNTRIES[0]);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }

    private void checkNumber(String phoneNumber) {

        CollectionReference getUser = mDb.collection(getString(R.string.collection_users));
        Query query = getUser.whereEqualTo("phoneNumber", phoneNumber);

        query.get().addOnSuccessListener(this, queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.getDocuments().isEmpty()) {
                Log.d(TAG, "checkNumber: find");
                MoveActivity.showToast(context, "Phone Number belum terdaftar");
            } else {
                moveToOtp(phoneNumber);
            }
        });
    }

    private void moveToOtp(String phoneNumber) {
        Log.d(TAG, "moveToOtp: " + phoneNumber);
        UserModel user = new UserModel();
        user.setPhoneNumber(phoneNumber);
        Intent i = new Intent(this, OtpActivity.class);
        i.putExtra(params, "login");
        i.putExtra(getString(R.string.intent_phone), user);
        startActivity(i);
    }

    @Override
    public void onValidationSucceeded() {
        Log.d(TAG, "onValidationSucceeded: valid");
        String phoneNumber = "+62" + inputPhone.getText().toString();
        checkNumber(phoneNumber);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
