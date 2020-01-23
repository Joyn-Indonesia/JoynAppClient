package com.example.joynappclient.ui.authentication.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.remote.model.UserModel;
import com.example.joynappclient.ui.authentication.otp.OtpActivity;
import com.example.joynappclient.ui.authentication.signin.SignInActivity;
import com.example.joynappclient.utils.MoveActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {
    private static final String TAG = "SignUpActivity";

    //wigets
    @NotEmpty
    @BindView(R.id.input_name)
    EditText name;

    @NotEmpty
    @Email
    @BindView(R.id.input_email)
    EditText email;

    @NotEmpty
    @Length(min = 10, max = 13, trim = true, message = "Phone number not valid")
    @BindView(R.id.input_phoneNumber)
    EditText phoneNumber;

    //vars
    private Context context;
    private Validator validator;
    private FirebaseFirestore db;
    private String phone = "+62";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        context = this;
        validator = new Validator(this);
        validator.setValidationListener(this);
        db = FirebaseFirestore.getInstance();
    }

    private void checkNumber() {

        if (phoneNumber.getText().toString().charAt(0) == '0') {
            phone = phone + phoneNumber.getText().toString().substring(1);
        }

        CollectionReference getUser = db.collection(getString(R.string.collection_users));
        Query query = getUser.whereEqualTo("phoneNumber", phone);
        query.get().addOnSuccessListener(this, queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.getDocuments().isEmpty()) {
                registerUser();
            } else {
                Toast.makeText(context, "Number has regristered", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser() {

        UserModel user = new UserModel();
        user.setName(name.getText().toString());
        user.seteMail(email.getText().toString());
        user.setPhoneNumber(phone);
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
        checkNumber();
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
