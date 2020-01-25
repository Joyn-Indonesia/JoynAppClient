package com.example.joynappclient.ui.authentication.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.remote.model.UserModel;
import com.example.joynappclient.ui.authentication.otp.OtpActivity;
import com.example.joynappclient.ui.authentication.signin.SignInActivity;
import com.example.joynappclient.utils.DialogActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.example.joynappclient.viewmodel.ViewModelFactory;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends DialogActivity implements Validator.ValidationListener {
    private static final String TAG = "SignUpActivity";

    //wigets
    @NotEmpty
    @BindView(R.id.input_name)
    EditText name;

    @NotEmpty
    @Email
    @BindView(R.id.input_email)
    EditText email;

    @BindView(R.id.phone_number)
    AutoCompleteTextView editTextFilledExposedDropdown;

    @NotEmpty
    @Length(min = 10, max = 13, trim = true, message = "Phone number not valid")
    @BindView(R.id.input_phoneNumber)
    EditText inputPhone;

    //vars
    private Context context;
    private Validator validator;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        context = this;

        ViewModelFactory factory = ViewModelFactory.getInstance();
        viewModel = new ViewModelProvider(this, factory).get(SignUpViewModel.class);

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
        viewModel.setCredential(phoneNumber, getString(R.string.collection_users));

        viewModel.checkPhoneNumber().observe(this, api -> {
            switch (api.status) {
                case LOADING:
                    showProgressDialog(R.string.dialog_loading);
                    break;
                case EMPTY:
                    registerUser(phoneNumber);
                    Log.d(TAG, "checkNumber: register");
                    hideProgressDialog();
                    break;
                case SUCCESS:
                    showToast(api.message);
                    Log.d(TAG, "checkNumber: number");
                    hideProgressDialog();
                    break;
            }
        });
    }

    private void registerUser(String phoneNumber) {
        Log.d(TAG, "registerUser: register run");
        UserModel user = new UserModel();
        user.setName(name.getText().toString());
        user.seteMail(email.getText().toString());
        user.setPhoneNumber(phoneNumber);
        Intent i = new Intent(context, OtpActivity.class);
        i.putExtra(getString(R.string.intent_phone), user);
        startActivity(i);
    }

    @OnClick(R.id.btn_regiter)
    public void register() {
        validator.validate();
    }

    @OnClick(R.id.tv_signIn)
    public void moveToSignin() {
        MoveActivity.MoveAct(context, SignInActivity.class);
    }

    @Override
    public void onValidationSucceeded() {
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
